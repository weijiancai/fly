/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.NotationBorder;
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import flash.geom.Point;

    public class ParallelNotation extends BaseNotation {
        public function ParallelNotation(id:String, name:String, x:Number, y:Number, width:Number, height:Number) {
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
            // 菱形中画十字
            this.graphics.lineStyle(4, 0x000000);
            this.graphics.moveTo(_x + 9, _y + _height/2);
            this.graphics.lineTo(_x + _width - 9, _y + _height/2); // 画横线
            this.graphics.moveTo(_x + _width/2, _y + 9);
            this.graphics.lineTo(_x + _width/2, _y + _height - 9); // 画竖线
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
