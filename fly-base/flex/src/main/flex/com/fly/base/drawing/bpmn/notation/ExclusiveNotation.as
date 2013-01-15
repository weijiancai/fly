/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import flash.geom.Point;

    public class ExclusiveNotation extends BaseNotation {
        public function ExclusiveNotation(name:String, x:Number, y:Number, width:Number, height:Number) {
            super(name, x, y, width, height);
        }


        override public function draw():void {
            // 设置坐标点
            this.x = _nX - _nWidth/2 - 10;
            this.y = _nY;
            var _x:Number =  0;
            var _y:Number = - _nHeight;
            var _width:Number = _nWidth;
            var _height:Number = _nHeight;
            // 画菱形
            NotationUtil.drawRhombus(graphics, 0, 0, _nWidth, -nHeight);
            // 菱形种画×
            this.graphics.lineStyle(4, 0x000000);
            var tmpStartPoint:Point = Point.interpolate(new Point(_x, _y + _height / 2), new Point(_x + _width / 2, _y), 0.5);
            var tmpEndPoint:Point = Point.interpolate(new Point(_x + _width, _y + _height / 2), new Point(_x + _width / 2, _y + _height), 0.5);
            var startPoint:Point = Point.interpolate(tmpStartPoint, tmpEndPoint, 0.2);
            var endPoint:Point = Point.interpolate(tmpStartPoint, tmpEndPoint, 0.8);
            this.graphics.moveTo(startPoint.x, startPoint.y);
            this.graphics.lineTo(endPoint.x, endPoint.y);

            tmpStartPoint = Point.interpolate(new Point(_x + _width / 2, _y), new Point(_x + _width, _y + _height / 2), 0.5);
            tmpEndPoint = Point.interpolate(new Point(_x + _width / 2, _y + _height), new Point(_x, _y + _height / 2), 0.5);
            startPoint = Point.interpolate(tmpStartPoint, tmpEndPoint, 0.2);
            endPoint = Point.interpolate(tmpStartPoint, tmpEndPoint, 0.8);
            this.graphics.moveTo(startPoint.x, startPoint.y);
            this.graphics.lineTo(endPoint.x, endPoint.y);
            // 画文件
            textField.x =  - _nWidth/2 - 8;
            textField.y = _nHeight/2;
            textField.width = _nWidth * 2.5;
            this.addChild(textField);
        }
    }
}
