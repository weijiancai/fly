/**
 * 业务流程模型符号
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.bpmn.event.NotationEvent;
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import flash.display.Graphics;

    import flash.events.MouseEvent;

    import flash.text.TextField;
    import flash.text.TextFieldAutoSize;

    import mx.containers.Canvas;
    import mx.controls.Image;
    import mx.effects.Glow;

    [Event(name=NotationEvent.ICON_MOUSE_DOWN, type="com.fly.base.drawing.bpmn.event.NotationEvent")]
    [Event(name=NotationEvent.ICON_MOUSE_UP, type="com.fly.base.drawing.bpmn.event.NotationEvent")]
    [Event(name=NotationEvent.ICON_MOVE, type="com.fly.base.drawing.bpmn.event.NotationEvent")]

    public class BPMNotation extends Image {
        public static const TYPE_START_EVENT:String = "startEvent";
        public static const TYPE_END_EVENT:String = "endEvent";
        public static const TYPE_USER_TASK:String = "userTask";
        public static const TYPE_SERVICE_TASK:String = "serviceTask";
        public static const TYPE_EXCLUSIVE_GATEWAY:String = "exclusiveGateway";
        public static const TYPE_PARALLEL_GATEWAY:String = "parallelGateway";
        public static const TYPE_SEQUENCE_FLOW:String = "sequenceFlow";
        public static const TYPE_LANE:String = "lane";

        // 名称
        private var _key:String;
        // 图标类型
        private var _type:String;
        // 图标上的文字说明
        private var _label:String;
        // 备注
        private var _memo:String;
        // 图标是否被选中
        public var isSelected:Boolean = false;
        // 图标是否高亮
        public var isHighlight:Boolean = false;
        // 图像数据
        private var _icon:Object;

        // X坐标
        private var _x:Number;
        // Y坐标
        private var _y:Number;
        // 宽度
        private var _width:Number;
        // 高度
        private var _height:Number;
        private var y_min:Number;

        private var glow:Glow = new Glow();
        private var _di:XML;
        private var text:TextField;

        private var selectedRectBorder:NotationBorder;
        private var highlightRectBorder:NotationBorder;


        public function BPMNotation(node:XML, di:XML, y_min:Number) {
            this._di = di;
            this.y_min = y_min;

            _key = node.@id.toString();
            _label = node.@name.toString();

            if (di != null) {
                if ("BPMNShape" == di.localName()) {
                    _x = di.children()[0].@x;
                    _y = Number(di.children()[0].@y) + y_min; // 距离最上面15
                    _width = di.children()[0].@width;
                    _height = di.children()[0].@height;
                }
            }

            this.graphics.clear();

            glow.blurXFrom = 0;
            glow.blurXTo = 15;
            glow.color = 0xFF0000;
            glow.blurYFrom = 0;
            glow.blurYTo = 15;

            if ("startEvent" == node.localName()) {
                _type = TYPE_START_EVENT;
            } else if ("endEvent" == node.localName()) {
                _type = TYPE_END_EVENT;
            } else if ("userTask" == node.localName()) {
                _type = TYPE_USER_TASK;
                this.source = ProcessPng.USER_TASK;
            } else if ("exclusiveGateway" == node.localName()) {
                _type = TYPE_EXCLUSIVE_GATEWAY;
            } else if ("parallelGateway" == node.localName()) {
                _type = TYPE_PARALLEL_GATEWAY;
            } else if ("serviceTask" == node.localName()) {
                _type = TYPE_SERVICE_TASK;
                this.source = ProcessPng.SERVICE_TASK;
            } else if ("sequenceFlow" == node.localName()) {
                _type = TYPE_SEQUENCE_FLOW;
            } else if("lane" == node.localName()) {
                _type = TYPE_LANE;
            }

            text = new TextField();
            text.autoSize = TextFieldAutoSize.LEFT;
            if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                text.width = _width;
            } else {
                text.width = _width * 2;
            }
            // 自动换行
            text.wordWrap = true;
            text.condenseWhite = true;
            text.multiline = true;

            this.draw();
            this.bindEvents();
        }

        public function get key():String {
            return _key;
        }

        public function set key(value:String):void {
            _key = value;
        }

        public function get type():String {
            return _type;
        }

        public function set type(value:String):void {
            _type = value;
        }

        public function get memo():String {
            return _memo;
        }

        public function set memo(value:String):void {
            _memo = value;
        }

        // 画图标
        private function draw():void {
            if(this._icon == null) {
                if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                    this.x = _x + 5;
                    this.y = _y + 1;
                }

                // 画文本
                if(TYPE_START_EVENT == this._type || TYPE_END_EVENT == _type || TYPE_EXCLUSIVE_GATEWAY == _type || TYPE_PARALLEL_GATEWAY == _type) {
                    text.x = _x - 3;
                    text.y = _y + 30 + 3;
                } else {
                    text.x = -2;
                    text.y = 11;
                }
                this.addChild(text);

                // 画圆角矩形
                if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                    NotationUtil.drawRect(graphics, -5.5, -1.5, _width, _height);// + 0.5抗锯齿
                } else if(TYPE_EXCLUSIVE_GATEWAY == _type || TYPE_PARALLEL_GATEWAY == _type) { // 画菱形
                    NotationUtil.drawRhombus(graphics, _x, _y, _width, _height);
                } else if(TYPE_SEQUENCE_FLOW == _type) { // 画线
                    NotationUtil.drawLine(graphics, _di, y_min);
                } else if(TYPE_LANE == _type) { // 画泳道
                    graphics.lineStyle(1);
                    graphics.drawRect(_x, _y, _width, _height);
                    graphics.moveTo(_x + 15, _y);
                    graphics.lineTo(_x + 15, _y + _height);
                    text.x = 0;
                    text.y = 0;
                }


                if(TYPE_START_EVENT == this._type) { // 画开始节点
                    NotationUtil.drawCircle(graphics, _x + _width / 2, _y + _height / 2);
                } else if(TYPE_END_EVENT == _type) { // 画结束节点
                    NotationUtil.drawCircle(graphics, _x + _width / 2, _y + _height / 2, 4);
                } else if(TYPE_PARALLEL_GATEWAY == _type) { // 菱形中画十字
                    this.graphics.lineStyle(4, 0x000000);
                    this.graphics.moveTo(_x + 9, _y + _height/2);
                    this.graphics.lineTo(_x + _width - 9, _y + _height/2); // 画横线
                    this.graphics.moveTo(_x + _width/2, _y + 9);
                    this.graphics.lineTo(_x + _width/2, _y + _height - 9); // 画竖线
                }
            } else {
                this.source = _icon;
            }
            text.htmlText = "<font size='12'>" + this._label + "</font>"
        }

        // 绑定事件
        private function bindEvents():void {
            this.addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
            this.addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
            this.addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler);
        }

        // 触发鼠标按下事件
        private function mouseDownHandler(event:MouseEvent):void {
            var e:NotationEvent = new NotationEvent(NotationEvent.ICON_MOUSE_DOWN);
            e.icon = this;
            glow.target = this;
            glow.end();
            glow.play();

            this.dispatchEvent(e);
        }

        // 触发鼠标弹起事件
        private function mouseUpHandler(event:MouseEvent):void {
            var e:NotationEvent = new NotationEvent(NotationEvent.ICON_MOUSE_UP);
            e.icon = this;
            this.dispatchEvent(e);
        }

        // 触发鼠标移动事件
        private function mouseMoveHandler(event:MouseEvent):void {
            var e:NotationEvent = new NotationEvent(NotationEvent.ICON_MOVE);
            e.icon = this;
            this.dispatchEvent(e);
        }

        // 高亮此节点
        public function highlight():void {
            if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                clearHighlight();
                highlightRectBorder = new NotationBorder(-5.5, -1.5, _width, _height, 1, 0xff0000);
                highlightRectBorder.drawRect();
                this.addChild(highlightRectBorder);
                this.isHighlight = true;
            }
        }

        // 清除高亮
        public function clearHighlight():void {
            if(highlightRectBorder != null && this.contains(highlightRectBorder)) {
                this.removeChild(highlightRectBorder);
                this.isHighlight = false;
                highlightRectBorder = null;
            }
        }

        // 选中
        public function selected():void {
            clearSelected();
            selectedRectBorder = new NotationBorder(_x, _y, _width,_height, 1, 0x0000ff, 0);
            if(TYPE_START_EVENT == this._type) { // 画开始节点
                selectedRectBorder._x = _x + _width / 2;
                selectedRectBorder._y = _y + _height / 2;
                selectedRectBorder.drawCircle();
            } else if(TYPE_END_EVENT == _type) { // 画结束节点
                selectedRectBorder._x = _x + _width / 2;
                selectedRectBorder._y = _y + _height / 2;
                selectedRectBorder._thickness = 4;
                selectedRectBorder.drawCircle();
            } else if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                selectedRectBorder._x = -5.5;
                selectedRectBorder._y = -1.5;
                selectedRectBorder.drawRect();
            } else if(TYPE_EXCLUSIVE_GATEWAY == _type || TYPE_PARALLEL_GATEWAY == _type) { // 画菱形
                selectedRectBorder.drawRhombus();
            } else if(TYPE_SEQUENCE_FLOW == _type) { // 画线
                selectedRectBorder.drawLine(_di, y_min);
            }
            this.addChild(selectedRectBorder);
            this.isSelected = true;
        }

        // 清除选中
        public function clearSelected():void {
            if(selectedRectBorder != null && this.contains(selectedRectBorder)) {
                this.removeChild(selectedRectBorder);
                this.isSelected = false;
                selectedRectBorder = null;
            }
        }
    }
}
