/**
 * 业务流程模型符号
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.bpmn.event.NotationEvent;

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
        // icon x,y坐标偏移位置
        public var icon_offset_x:int = 5;
        public var icon_offset_y:int = 3;
        // text x,y坐标偏移位置
        public var text_offset_x:int = 4;
        public var text_offset_y:int = 15;

        private var glow:Glow = new Glow();
        private var _di:XML;

        private var selectedRectBorder:NotationBorder;
        private var highlightRectBorder:NotationBorder;


        public function BPMNotation(node:XML, di:XML) {
            this._di = di;

            _key = node.@id.toString();
            _label = node.@name.toString();

            if (di != null) {
                if ("BPMNShape" == di.localName()) {
                    _x = di.children()[0].@x;
                    _y = di.children()[0].@y;
                    _width = di.children()[0].@width;
                    _height = di.children()[0].@height;
//                    this.x = _x;
//                    this.y = _y;
//                    this.width = _width;
//                    this._height = _height;
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
                drawStartEvent();
            } else if ("endEvent" == node.localName()) {
                _type = TYPE_END_EVENT;
                drawEndEvent();
            } else if ("userTask" == node.localName()) {
                _type = TYPE_USER_TASK;
                drawUserTask();
            } else if ("exclusiveGateway" == node.localName()) {
                _type = TYPE_EXCLUSIVE_GATEWAY;
                drawExclusiveGateway();
            } else if ("parallelGateway" == node.localName()) {
                _type = TYPE_PARALLEL_GATEWAY;
                drawParallelGateway();
            } else if ("serviceTask" == node.localName()) {
                _type = TYPE_SERVICE_TASK;
                drawServiceTask();
            } else if ("sequenceFlow" == node.localName()) {
                _type = TYPE_SEQUENCE_FLOW;
                drawSequenceFlow();
            }

//            this.draw();
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

        // 画开始节点
        public function drawStartEvent():void {
            // 添加文本节点
            var text:NotationText = new NotationText(_label, _width*2, _x - 3, _y + 30 + 3);
            this.addChild(text);
            // 添加边框
            var border:NotationBorder = new NotationBorder(_x + _width / 2, _y + _height / 2, _width, _height, 1, 0, 0xffffff);
            border.drawCircle();
            this.addChild(border);
        }

        // 画结束节点
        public function drawEndEvent():void {
            var border:NotationBorder = new NotationBorder(_x + _width / 2, _y + _height / 2, _width, _height, 4, 0, 0xffffff);
            border.drawCircle();
            this.addChild(border);
            // 添加文本节点
            var text:NotationText = new NotationText(_label, _width*2, _x - 3, _y + 30 + 3);
            this.addChild(text);
        }

        // 画用户任务节点
        public function drawUserTask():void {
            this.x = _x + 5;
            this.y = _y + 1;
            this.source = ProcessPng.USER_TASK;
            var border:NotationBorder = new NotationBorder(-5.5, -1.5, _width, _height, 1, 0, 0xffffff);
            border.drawRect(20, 20);
            this.addChild(border);
            // 添加文本节点
            var text:NotationText = new NotationText(_label, _width, -2, 11);
            this.addChild(text);
        }

        public function drawExclusiveGateway():void {
            var border:NotationBorder = new NotationBorder(_x, _y, _width, _height, 1, 0, 0xffffff);
            border.drawRhombus();
            this.addChild(border);
            // 添加文本节点
            var text:NotationText = new NotationText(_label, _width*2, _x -5, _y + text_offset_y + _height/2);
            this.addChild(text);
        }

        public function drawParallelGateway():void {
            var border:NotationBorder = new NotationBorder(_x, _y, _width, _height, 1, 0, 0xffffff);
            border.drawRhombus();
            // 菱形中画十字
            border.graphics.lineStyle(4, 0x000000);
            border.graphics.moveTo(_x + 9, _y + _height/2);
            border.graphics.lineTo(_x + _width - 9, _y + _height/2); // 画横线
            border.graphics.moveTo(_x + _width/2, _y + 9);
            border.graphics.lineTo(_x + _width/2, _y + _height - 9); // 画竖线
            this.addChild(border);
            // 添加文本节点
            var text:NotationText = new NotationText(_label, _width*2, _x - 5, _y + text_offset_y + _height/2);
            this.addChild(text);
        }

        // 画服务任务节点
        public function drawServiceTask():void {
            this.x = _x + 5;
            this.y = _y + 1;
            this.source = ProcessPng.SERVICE_TASK;
            var border:NotationBorder = new NotationBorder(-5.5, -1.5, _width, _height, 1, 0, 0xffffff);
            border.drawRect(20, 20);
            this.addChild(border);
            // 添加文本节点
            var text:NotationText = new NotationText(_label, _width, -2, 11);
            this.addChild(text);
        }

        // 画连接线
        private function drawSequenceFlow():void {
            var border:NotationBorder = new NotationBorder(_x, _y, _width, _height, 1, 0, 0xffffff);
            border.drawLine(_di);
            this.addChild(border);
            // 添加文本节点
            var text:NotationText = new NotationText(_label, _width, _x + text_offset_x, _y + text_offset_y);
            this.addChild(text);
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
            /*if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                clearHighlight();
                highlightRectBorder = new NotaionEvent();
                highlightRectBorder.drawRect(2, 0xff0000, _width, _height, 0, 20, 20, -5.5, -1.5, false);
                this.addChild(highlightRectBorder);
                this.isHighlight = true;
            }*/
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
            /*selectedRectBorder = new NotaionEvent();
            if(TYPE_START_EVENT == this._type) { // 画开始节点
                selectedRectBorder.drawCircle(1, 0x0000ff, _x + _width / 2, _y + _height / 2);
            } else if(TYPE_END_EVENT == _type) { // 画结束节点
                selectedRectBorder.drawCircle(4, 0x0000ff, _x + _width / 2, _y + _height / 2);
            } else if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                selectedRectBorder.drawRect(1, 0x0000ff, _width, _height, 0, 20, 20, -5.5, -1.5);
            } else if(TYPE_EXCLUSIVE_GATEWAY == _type || TYPE_PARALLEL_GATEWAY == _type) { // 画菱形
                selectedRectBorder.drawRhombus(1, 0x0000ff, _width, _height, 0, _x,  _y);
            } else if(TYPE_SEQUENCE_FLOW == _type) { // 画线
                selectedRectBorder.drawLine(_di, 1, 0x0000ff);
            }
            this.addChild(selectedRectBorder);
            this.isSelected = true;*/
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
