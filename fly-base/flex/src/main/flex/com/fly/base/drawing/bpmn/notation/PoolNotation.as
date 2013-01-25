/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {

    public class PoolNotation extends BaseNotation {
        public function PoolNotation(id:String, name:String, x:Number, y:Number, width:Number, height:Number) {
            super(id, name, x, y, width, height);
        }

        override public function draw():void {
            // 画矩形
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawRect(-2.5, -1.5, _nWidth, _nHeight);
            /*graphics.moveTo(15, -1.5);
            graphics.lineTo(15, -1.5 + _nHeight);*/
        }
    }
}
