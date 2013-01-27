/**
 * 流程符号工具类
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.util {
    import com.fly.base.drawing.Pen;

    import flash.display.Graphics;

    public class NotationUtil {
        /**
         * 绘制一个矩形边框
         *
         * @param eclipseWidth
         * @param eclipseHeight
         * @param x
         * @param y
         * @param height
         * @param thickness
         * @param graphics
         * @param color
         * @param width
         * @param fillColor
         */
        public static function drawRect(graphics:Graphics, x:Number = 0, y:Number = 0, width:Number = 0, height:Number = 0, thickness:Number = 1, color:uint = 0, fillColor:int = 0xffffff, eclipseWidth:Number = 20, eclipseHeight:Number = 20):void {
            graphics.lineStyle(thickness, color, 1, true);
            graphics.beginFill(fillColor, 0.4);
            graphics.drawRoundRect(x, y, width, height, eclipseWidth, eclipseHeight);
            graphics.endFill();
        }

        /**
         * 绘制一个圆形边框
         *
         * @param radius
         * @param x
         * @param y
         * @param thickness
         * @param graphics
         * @param fillColor
         * @param color
         */
        public static function drawCircle(graphics:Graphics, x:Number = 0, y:Number = 0, thickness:Number = 1, color:uint = 0, fillColor:int = 0xffffff, radius:Number = 15):void {
            graphics.lineStyle(thickness, color, 1, true);
            graphics.beginFill(fillColor, 0.4);
            graphics.drawCircle(x, y, radius);
            graphics.endFill();
        }

        /**
         * 绘制一个菱形边框
         */
        public static function drawRhombus(graphics:Graphics, x:Number = 0, y:Number = 0, width:Number = 0, height:Number = 0, thickness:Number = 1, color:uint = 0, fillColor:int = 0xffffff):void {
            var pen:Pen = new Pen(graphics);
            pen.lineStyle(thickness, color, 1, true);
            pen.beginFill(fillColor, 0.4);
            pen.drawRhombus(x, y, width, height);
            pen.endFill();
        }

        /**
         * 绘制连接线边框
         *
         * @param di
         * @param radius
         * @param thickness
         * @param color
         * @param graphics
         * @param y_min
         */
        public static function drawLine(graphics:Graphics, di:XML, y_min:Number = 0, radius:Number = 5, thickness:Number = 1, color:uint = 0):void {
            if(di == null) {
                return;
            }
            var pen:Pen = new Pen(graphics);
            pen.lineStyle(thickness, color, 1, true);
            var waypointList:XMLList = di.children();
            var startX:Number = 0, startY:Number = 0, endX:Number = 0, endY:Number = 0;
            for (var j:int = 0; j < waypointList.length(); j++) {
                if(waypointList[j].localName() == "waypoint") {
                    if (endX > 0 && endY > 0) {
                        pen.drawLine(endX, endY, Number(waypointList[j].@x), Number(waypointList[j].@y) + y_min);
                    }

                    endX = Number(waypointList[j].@x);
                    endY = Number(waypointList[j].@y) + y_min;

                    if (j == waypointList.length() - 2) { // 记录最后一个开始点的位置
                        startX = endX;
                        startY = endY;
                    }
                }
            }

            // 画箭头
            drawArrow(pen, startX, startY, endX, endY, radius);
        }

        public static function drawArrow(pen:Pen, startX:Number, startY:Number, endX:Number, endY:Number, radius:Number = 5):void {
            var angle:int = getAngle(startX, startY, endX, endY);
            var centerX:int = endX - radius * Math.cos(angle *(Math.PI/180));
            var centerY:int= endY + radius * Math.sin(angle *(Math.PI/180));
            var leftX:int=centerX + radius * Math.cos((angle +120) *(Math.PI/180));
            var leftY:int=centerY - radius * Math.sin((angle +120) *(Math.PI/180));

            var rightX:int=centerX + radius * Math.cos((angle +240) *(Math.PI/180));
            var rightY:int=centerY - radius * Math.sin((angle +240) *(Math.PI/180));

            pen.beginFill(0x000000, 1);

            pen.lineStyle(1, 0x000000, 1);

            pen.moveTo(endX, endY);
            pen.lineTo(leftX,leftY);

            pen.lineTo(centerX,centerY);

            pen.lineTo(rightX,rightY);
            pen.lineTo(endX, endY);

            pen.endFill();
        }

        private static function getAngle(startX:Number, startY:Number, endX:Number, endY:Number):int{
            var tmpX:int = endX - startX;
            var tmpY:int = startY - endY;
            return Math.atan2(tmpY, tmpX) * (180/Math.PI);
        }
    }
}
