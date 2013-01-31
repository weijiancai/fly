/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.NotationBorder;
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import flash.geom.Point;

    public class ExclusiveNotation extends BaseNotation {
        public function ExclusiveNotation(id:String, name:String, x:Number, y:Number, width:Number, height:Number) {
            super(id, name, x, y, width, height);
        }

        override public function draw():void {
            // 设置坐标点
            var _x:Number =  0;
            var _y:Number = - _nHeight;
            var _width:Number = _nWidth;
            var _height:Number = _nHeight;
            // 画菱形
            NotationUtil.drawRhombus(graphics, 0, -_nHeight, _nWidth, _nHeight);
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
        }

        override protected function getTextFieldXPos():Number {
            return textFieldOuterXPos;
        }

        override protected function getTextFiledYPos():Number {
            return 5;
        }

        override protected function getTextFieldWidth():Number {
            return 100;
        }

        override protected function getHighlightBorder():NotationBorder {
            var highlightRectBorder:NotationBorder = new NotationBorder(0, -_nHeight, _nWidth, _nHeight, 2, 0xff0000);
            highlightRectBorder.drawRhombus();
            return highlightRectBorder;
        }

        override protected function getSelectedBorder():NotationBorder {
            return super.getSelectedBorder();
        }
    }
}
