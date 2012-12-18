/**
 * BPMN流程图符号边框
 *
 * @author wei_jc
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.Pen;
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import flash.display.Shape;

    public class NotationBorder extends Shape {
        public var _thickness:Number;	// 线条宽度
        public var _color:uint;		// 线条颜色
        public var _width:uint;		// 矩形宽
        public var _height:uint;		// 矩形高
        public var _fillColor:uint;	// 填充 颜色
        public var _x:int; // 矩形的x坐标
        public var _y:int; // 矩形的y坐标
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
        public function drawRect(eclipseWidth:Number = 20, eclipseHeight:Number = 20):void {
            NotationUtil.drawRect(graphics, _x, _y, _width, _height, _thickness, _color, _fillColor, eclipseWidth, eclipseHeight);
        }

        /**
         * 绘制一个圆形边框
         *
         * @param radius
         */
        public function drawCircle(radius:Number = 15):void {
            NotationUtil.drawCircle(graphics, _x, _y, _thickness, _color, _fillColor, radius);
        }

        /**
         * 绘制一个菱形边框
         */
        public function drawRhombus():void {
            NotationUtil.drawRhombus(graphics, _x, _y, _width, _height, _thickness, _color, _fillColor);
        }

        /**
         * 绘制连接线边框
         *
         * @param di
         * @param radius
         * @param y_min
         */
        public function drawLine(di:XML, y_min:Number = 0, radius:Number = 5):void {
            NotationUtil.drawLine(graphics, di, y_min, radius, _thickness, _color);
        }
    }
}
