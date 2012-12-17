/**
 * BPMN流程图符号边框
 *
 * @author wei_jc
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.Pen;

    import flash.display.Shape;

    public class NotationBorder extends Shape {
        private var _thickness:Number;	// 线条宽度
        private var _color:uint;		// 线条颜色
        private var _width:uint;		// 矩形宽
        private var _height:uint;		// 矩形高
        private var _fillColor:uint;	// 填充 颜色
        private var _x:int; // 矩形的x坐标
        private var _y:int; // 矩形的y坐标
        private var isFill:Boolean = false;

        private var pen:Pen; // 画笔

        public function NotationBorder(x:Number = 0, y:Number = 0, width:Number = 0, height:Number = 0, thickness:Number = 1, color:uint = 0, fillColor:int = -1) {
            super();
            this._x = x;
            this._y = y;
            this._width = width;
            this._height = height;
            this._thickness = thickness;
            this._color = color;
            this._fillColor = fillColor;
            if(fillColor != -1) {
                isFill = true;
            }

            pen = new Pen(this.graphics);
        }

        /**
         * 绘制一个矩形边框
         *
         * @param eclipseWidth
         * @param eclipseHeight
         */
        public function drawRect(eclipseWidth:Number = 0, eclipseHeight:Number = 0):void {
            pen.lineStyle(_thickness, _color, 1, true);
            if (isFill) {
                pen.beginFill(_fillColor, 0.4);
            }
            pen.drawRoundRect(_x, _y, _width, _height, eclipseWidth, eclipseHeight);
            if (isFill) {
                pen.endFill();
            }
        }

        /**
         * 绘制一个圆形边框
         *
         * @param radius
         */
        public function drawCircle(radius:Number = 15):void {
            pen.lineStyle(_thickness, _color, 1, true);
            if(isFill) {
                pen.beginFill(_fillColor, 0.4);
            }
            pen.drawCircle(_x, _y, radius);
            if(isFill) {
                pen.endFill();
            }
        }

        /**
         * 绘制一个菱形边框
         */
        public function drawRhombus():void {
            pen.lineStyle(_thickness, _color, 1, true);
            if(isFill) {
                pen.beginFill(_fillColor, 0.4);
            }
            pen.drawRhombus(_x, _y, _width, _height);
            if(isFill) {
                pen.endFill();
            }
        }

        /**
         * 绘制连接线边框
         *
         * @param di
         * @param radius
         */
        public function drawLine(di:XML, radius:Number = 5):void {
            pen.lineStyle(_thickness, _color, 1, true);
            var waypointList:XMLList = di.children();
            var startX:Number = 0, startY:Number = 0, endX:Number = 0, endY:Number = 0;
            for (var j:int = 0; j < waypointList.length(); j++) {
                if (endX > 0 && endY > 0) {
                    pen.drawLine(endX, endY, Number(waypointList[j].@x), Number(waypointList[j].@y));
                }

                endX = Number(waypointList[j].@x);
                endY = Number(waypointList[j].@y);

                if (j == waypointList.length() - 2) { // 记录最后一个开始点的位置
                    startX = endX;
                    startY = endY;
                }
            }

            // 画箭头
            drawArrow(startX, startY, endX, endY, radius);
        }

        private function drawArrow(startX:Number, startY:Number, endX:Number, endY:Number, radius:Number):void {
            var angle:int = getAngle(startX, startY, endX, endY);
            var centerX:int = endX - radius * Math.cos(angle *(Math.PI/180));
            var centerY:int= endY + radius * Math.sin(angle *(Math.PI/180));
            var leftX:int=centerX + radius * Math.cos((angle +120) *(Math.PI/180));
            var leftY:int=centerY - radius * Math.sin((angle +120) *(Math.PI/180));

            var rightX:int=centerX + radius * Math.cos((angle +240) *(Math.PI/180));
            var rightY:int=centerY - radius * Math.sin((angle +240) *(Math.PI/180));

            this.graphics.beginFill(0x000000, 1);

            this.graphics.lineStyle(1, 0x000000, 1);

            this.graphics.moveTo(endX, endY);
            this.graphics.lineTo(leftX,leftY);

            this.graphics.lineTo(centerX,centerY);

            this.graphics.lineTo(rightX,rightY);
            this.graphics.lineTo(endX, endY);

            this.graphics.endFill();
        }

        private static function getAngle(startX:Number, startY:Number, endX:Number, endY:Number):int{
            var tmpX:int = endX - startX;
            var tmpY:int = startY - endY;
            return Math.atan2(tmpY, tmpX) * (180/Math.PI);
        }
    }
}
