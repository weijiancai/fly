/**
 *
 * @author wei_jc
 * @version 0.0.1
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.NotationBorder;

    public class StartEventNotation extends BaseNotation {
        public function StartEventNotation(id:String, name:String, x:Number, y:Number, width:Number = 30, height:Number = 30) {
            super(id, name, x, y, width, height);
        }

        override public function draw():void {
            // 画圆
            graphics.lineStyle(1, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawCircle(0, 0, 30/2);
            graphics.endFill();
        }

        override protected function getTextFieldXPos():Number {
            return textFieldOuterXPos - 15;
        }

        override protected function getTextFiledYPos():Number {
            return textFieldOuterYPos - 10;
        }

        override protected function getTextFieldWidth():Number {
            return 100;
        }

        override protected function getHighlightBorder():NotationBorder {
            var highlightRectBorder:NotationBorder = new NotationBorder(-0.5, -0.5, _nWidth, _nHeight, 2, 0xff0000);
            highlightRectBorder.drawCircle();
            return highlightRectBorder;
        }

        override protected function getSelectedBorder():NotationBorder {
            return super.getSelectedBorder();
        }
    }
}
