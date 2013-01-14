/**
 *
 * @author wei_jc
 * @version 0.0.1
 */
package com.fly.base.drawing.bpmn.notation {
    public class StartEventNotation extends BaseNotation {
        public function StartEventNotation(name:String, x:Number, y:Number, width:Number = 15, height:Number = 15) {
            super(name, x, y, width, height);
        }

        override public function draw():void {
            // 设置坐标点
            this.x = _nX - _nWidth/2 - 5;
            this.y = _nY - _nHeight/2 - 5;
            // 画圆
            graphics.lineStyle(1, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawCircle(0, 0, _nWidth);
            graphics.endFill();
            // 画文件
            textField.x =  - _nWidth/2 - 8;
            textField.y = _nHeight/2 + 8;
            textField.width = _nWidth * 2;
            this.addChild(textField);
        }
    }
}
