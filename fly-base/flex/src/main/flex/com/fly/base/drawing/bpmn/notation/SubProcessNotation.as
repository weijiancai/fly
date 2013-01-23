/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {

    public class SubProcessNotation extends BaseNotation {
        public function SubProcessNotation(name:String, x:Number, y:Number, width:Number, height:Number) {
            super(name, x, y, width, height);
        }


        override public function draw():void {
            // 画矩形
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawRoundRect(-2.5, -1.5, _nWidth, _nHeight, 5, 5);
            // 画文本
            textField.x =  15;
            textField.y = 0;
            this.addChild(textField);
        }
    }
}
