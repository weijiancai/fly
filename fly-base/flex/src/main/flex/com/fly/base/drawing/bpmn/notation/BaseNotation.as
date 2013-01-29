/**
 *
 * @author wei_jc
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.NotationBorder;
    import com.fly.base.drawing.bpmn.event.NotationEvent;

    import flash.events.MouseEvent;
    import flash.text.TextField;

    import mx.controls.Image;
    import mx.effects.Glow;

    public class BaseNotation extends Image implements INotation {
        protected var _nX:Number;
        protected var _nY:Number;
        protected var _nWidth:Number;
        protected var _nHeight:Number;

        protected var _nId:String;
        protected var _nName:String;

        protected var _thickness:Number = 1;
        protected var _color:uint = 0;
        protected var _fillColor:uint = 0xffffff;
        protected var _eclipseWidth:Number = 20;
        protected var _eclipseHeight:Number = 20;

        protected var textField:TextField;
        protected var textFieldInnerYPos:Number = 0;
        protected var textFieldOuterYPos:Number = 0;
        protected var textFieldInnerXPos:Number = 0;
        protected var textFieldOuterXPos:Number = 0;

        private var glow_down:Glow = new Glow(); // 鼠标按下发光效果
        private var glow_up:Glow = new Glow(); // 鼠标弹起发光效果

        // 图标是否被选中
        public var isSelected:Boolean = false;
        // 图标是否高亮
        public var isHighlight:Boolean = false;
        private var selectedRectBorder:NotationBorder;
        private var highlightRectBorder:NotationBorder;

        public function BaseNotation(id:String, name:String, x:Number, y:Number, width:Number, height:Number) {
            _nId = id;
            _nName = name;
            this.x = _nX = x;
            this.y = _nY = y;
            _nWidth = width;
            _nHeight = height;

            glow_down.blurXFrom = 0;
            glow_down.blurXTo = 15;
            glow_down.color = 0xFF0000;
            glow_down.blurYFrom = 0;
            glow_down.blurYTo = 15;

            glow_up.alphaFrom = 50;
            glow_up.alphaTo = 0;
            glow_up.blurXFrom = 15;
            glow_up.blurXTo = 0;
            glow_up.color = 0x00FF00;
            glow_up.blurYFrom = 15;
            glow_up.blurYTo = 0;

            textField = new TextField();
            // 自动换行
            textField.wordWrap = true;
            textField.condenseWhite = true;
            textField.multiline = true;
            textField.htmlText = "<font size='12'>" + name + "</font>";

            if(textField.textWidth <= _nWidth) {
                textFieldInnerXPos = (_nWidth - textField.textWidth)/2 - 1;
            }
            textFieldInnerYPos = (_nHeight - textField.textHeight)/2 - 3;
//            textFieldOuterXPos = (textField.textWidth - _nWidth)/2;
            textFieldOuterXPos = (_nWidth - textField.textWidth)/2 - 1;
            textFieldOuterYPos = _nHeight + 5;
            textField.width = getTextFieldWidth();
            textField.x = getTextFieldXPos();
            textField.y = getTextFiledYPos();

            this.addChild(textField);
//            textField.height = height;

            bindEvents();
            draw();
        }

        // 获取文本的Y位置, 默认取内部Y位置
        protected function getTextFiledYPos():Number {
            return textFieldInnerYPos;
        }

        // 获取文本的X位置, 默认取内部X位置
        protected function getTextFieldXPos():Number {
            return textFieldInnerXPos;
        }

        // 获取文本的宽度，默认返回_nWidth
        protected function getTextFieldWidth():Number {
            return _nWidth;
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
            e._icon = this;
            glow_down.target = this;
            glow_down.end();
            glow_down.play();

            this.dispatchEvent(e);
        }

        // 触发鼠标弹起事件
        private function mouseUpHandler(event:MouseEvent):void {
            var e:NotationEvent = new NotationEvent(NotationEvent.ICON_MOUSE_UP);
            e._icon = this;
            glow_up.target = this;
            glow_up.end();
            glow_up.play();

            this.dispatchEvent(e);
        }

        // 触发鼠标移动事件
        private function mouseMoveHandler(event:MouseEvent):void {
            var e:NotationEvent = new NotationEvent(NotationEvent.ICON_MOVE);
            e._icon = this;
            this.dispatchEvent(e);
        }

        // 高亮此节点
        public function highlight():void {
            clearHighlight();
//            highlightRectBorder = new NotationBorder(-0.5, -0.5, _nWidth, _nHeight, 1, 0xff0000);
//            highlightRectBorder.drawRect();
            highlightRectBorder = getHighlightBorder();
            this.addChild(highlightRectBorder);
            this.isHighlight = true;
        }

        // 获取高亮边框
        protected function getHighlightBorder():NotationBorder {
            return null;
        }

        // 清除高亮
        public function clearHighlight():void {
            if(highlightRectBorder != null && this.contains(highlightRectBorder)) {
                this.removeChild(highlightRectBorder);
                this.isHighlight = false;
                highlightRectBorder = null;
            }
        }

        // 获取选中边框
        private function getSelectedBorder():NotationBorder {
            return null;
        }

        // 清除选中
        public function clearSelected():void {
            if(selectedRectBorder != null && this.contains(selectedRectBorder)) {
                this.removeChild(selectedRectBorder);
                this.isSelected = false;
                selectedRectBorder = null;
            }
        }

        public function get nX():Number {
            return _nX;
        }

        public function set nX(value:Number):void {
            _nX = value;
        }

        public function get nY():Number {
            return _nY;
        }

        public function set nY(value:Number):void {
            _nY = value;
        }

        public function get nWidth():Number {
            return _nWidth;
        }

        public function set nWidth(value:Number):void {
            _nWidth = value;
        }

        public function get nHeight():Number {
            return _nHeight;
        }

        public function set nHeight(value:Number):void {
            _nHeight = value;
        }

        public function get nId():String {
            return _nId;
        }

        public function set nId(value:String):void {
            _nId = value;
        }

        public function get nName():String {
            return _nName;
        }

        public function set nName(value:String):void {
            _nName = value;
        }

        public function draw():void {
        }
    }
}
