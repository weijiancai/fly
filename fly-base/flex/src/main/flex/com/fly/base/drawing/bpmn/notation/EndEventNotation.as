/**
 *
 * @author weijiancai
 * @version 0.0.1
 */
package com.fly.base.drawing.bpmn.notation {
    public class EndEventNotation extends BaseNotation {
        public function EndEventNotation(id:String, name:String, x:Number, y:Number, width:Number = 30, height:Number = 30) {
            super(id, name, x, y, width, height);
        }

        override public function draw():void {
            // 画圆
            graphics.lineStyle(4, _color, 1, true);
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
    }
}
