/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {

    public class LaneNotation extends BaseNotation {
        public function LaneNotation(id:String, name:String, x:Number, y:Number, width:Number, height:Number) {
            super(id, name, x, y, width, height);
        }


        override public function draw():void {
            // 画矩形
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawRect(-0.5, -1.5, _nWidth, _nHeight);
            graphics.beginFill(0xffffff);
            graphics.drawRect(-0.5, -1.5, 15, _nHeight);
            graphics.endFill();
            // 画文本
            textField.x =  0;
            textField.y = (_nHeight - textField.textWidth)/2;
            textField.width = 15;
            this.addChild(textField);
        }
    }
}
