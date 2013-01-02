/**
 *
 * @author weijiancai
 */
package com.fly.base.example {
    import flash.events.Event;
    import flash.events.MouseEvent;
    import flash.ui.Mouse;
    import flash.ui.MouseCursor;

    import mx.containers.Canvas;

    public class ShowCurve extends Canvas {
        private var isStop:Boolean;

        public function ShowCurve() {
            init();
        }

        private function init():void {
            addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
            addEventListener(Event.ENTER_FRAME, enterFrameHandler);
            isStop = false;
            Mouse.cursor = MouseCursor.BUTTON;
        }

        private function mouseDownHandler(event:MouseEvent):void {
            if(!isStop) {
                removeEventListener(Event.ENTER_FRAME, enterFrameHandler);
                isStop = true;
            } else {
                addEventListener(Event.ENTER_FRAME, enterFrameHandler);
                isStop = false;
            }
        }

        private function enterFrameHandler(event:Event):void {
            graphics.clear();
            var numPoints:uint = 9;
            // 先对点初始化
            var points:Array = [];
            for(var i:int = 0; i < numPoints; i++) {
                points[i] = {};
                points[i].x = stage.stageWidth * Math.random();
                points[i].y = stage.stageHeight * Math.random();
                graphics.lineStyle(2, 0xff0000, 1);
                graphics.drawCircle(points[i].x,  points[i].y, 1);  // 为了更直观，把这几个点都圈出来
            }

            var _x_begin:Number = (points[0].x + points[1].x) / 2;
            var _y_begin:Number = (points[0].y + points[1].y) / 2;
            graphics.lineStyle(1, 0x00ff00, 1);
            graphics.drawCircle(_x_begin, _y_begin, 1);

            // 为了看得更清楚，把新加的点，用蓝色标出来
            for(i = 1; i < numPoints - 2; i++) {
                var _xc:Number = (points[i].x + points[i + 1].x) / 2;
                var _yc:Number = (points[i].y + points[i + 1].y) / 2;
                graphics.lineStyle(3, 0x0000ff, 1);
                graphics.drawCircle(_xc, _yc, 1);
            }
            graphics.lineStyle(1);

            // 先把画笔移到第一个帮助点
            graphics.moveTo(_x_begin, _y_begin);
            // 去掉首尾二点后，按照剩下的点和新加的点画曲线
            for(i = 1; i < numPoints - 2; i++) {
                var xc:Number = (points[i].x + points[i + 1].x) / 2;
                var yc:Number = (points[i].y + points[i + 1].y) / 2;
                graphics.curveTo(points[i].x, points[i].y, xc, yc);
            }
            var _len:uint = points.length;

            // 倒数第二个绿点
            var _x_end_1:Number = (points[_len - 2].x + points[_len -1].x) / 2;
            var _y_end_1:Number = (points[_len - 2].y + points[_len -1].y) / 2;
            // 最后一个绿点
            var _x_end_2:Number = (points[_len - 1].x + points[0].x) / 2;
            var _y_end_2:Number = (points[_len - 1].y + points[0].y) / 2;
            // 最后一个蓝点为出发点，到_x_end_1, _y_end_1，倒数第二个红点为把握点
            graphics.curveTo(points[i].x, points[i].y, _x_end_1, _y_end_1);
            graphics.curveTo(points[_len - 1].x, points[_len - 1].y, _x_end_2, _y_end_2);
            graphics.curveTo(points[0].x, points[0].y, _x_begin, _y_begin);
            graphics.lineStyle(1, 0x00ff00, 1);
            graphics.drawCircle(_x_end_1, _y_end_1, 1);
            graphics.drawCircle(_x_end_2, _y_end_2, 1);
        }
    }
}
