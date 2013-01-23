/**
 *
 * @author weijiancai
 * @version 0.0.1
 */
package com.fly.base.drawing.bpmn.notation {
    public class EndEventNotation extends BaseNotation {
        public function EndEventNotation(name:String, x:Number, y:Number, width:Number = 15, height:Number = 15) {
            super(name, x, y, width, height);
        }

        override public function draw():void {
            // 画圆
            graphics.lineStyle(4, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawCircle(0, 0, _nWidth);
            graphics.endFill();
            // 画文件
            textField.x =  - _nWidth/2 - 5;
            textField.y = _nHeight/2 + 10;
            textField.width = _nWidth * 2;
            this.addChild(textField);
        }
    }
}
