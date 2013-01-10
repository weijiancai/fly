/**
 *
 * @author wei_jc
 * @version 0.0.1
 */
package com.fly.base.drawing.bpmn.notation {
    public class StartEventNotation extends BaseNotation {
        public function StartEventNotation(x:Number, y:Number) {
            super();

            _nX = x;
            _nY = y;

            draw();
        }

        override public function draw():void {
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawCircle(0, 0, _radius);
            graphics.endFill();
        }
    }
}
