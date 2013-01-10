/**
 *
 * @author wei_jc
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.event.NotationEvent;

    import flash.events.MouseEvent;

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
        protected var _radius:Number = 15;

        private var glow_down:Glow = new Glow(); // 鼠标按下发光效果
        private var glow_up:Glow = new Glow(); // 鼠标弹起发光效果

        public function BaseNotation() {
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

            bindEvents();
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
