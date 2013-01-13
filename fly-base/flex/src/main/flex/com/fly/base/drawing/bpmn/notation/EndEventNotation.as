/**
 *
 * @author weijiancai
 * @version 0.0.1
 */
package com.fly.base.drawing.bpmn.notation {
    public class EndEventNotation extends BaseNotation {
        public function EndEventNotation(x:Number, y:Number, width:Number = 15, height:Number = 15) {
            super();

            _nX = x;
            _nY = y;
            _nWidth = width;
            _nHeight = height;

            draw();
        }

        override public function draw():void {
            this.x = _nX - _nWidth/2 - 5;
            this.y = _nY - _nHeight/2 - 5;
            graphics.lineStyle(4, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawCircle(0, 0, _nWidth);
            graphics.endFill();
        }
    }
}
