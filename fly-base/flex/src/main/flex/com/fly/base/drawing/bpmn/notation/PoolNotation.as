/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {

    public class PoolNotation extends BaseNotation {
        public function PoolNotation(name:String, x:Number, y:Number, width:Number, height:Number) {
            super(name, x, y, width, height);
        }


        override public function draw():void {
            // 画矩形
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawRect(-2.5, -1.5, _nWidth, _nHeight);
            graphics.moveTo(15, -1.5);
            graphics.lineTo(15, -1.5 + _nHeight);
            // 画文本
            textField.x =  0;
            textField.y = _nHeight/2 - 5;
            textField.width = 15;
            this.addChild(textField);
        }
    }
}
