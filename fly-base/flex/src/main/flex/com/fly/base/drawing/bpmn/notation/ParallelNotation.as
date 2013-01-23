/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import flash.geom.Point;

    public class ParallelNotation extends BaseNotation {
        public function ParallelNotation(name:String, x:Number, y:Number, width:Number, height:Number) {
            super(name, x, y, width, height);
        }


        override public function draw():void {
            // 设置坐标点
            var _x:Number =  0;
            var _y:Number = - _nHeight;
            var _width:Number = _nWidth;
            var _height:Number = _nHeight;
            // 画菱形
            NotationUtil.drawRhombus(graphics, 0, 0, _nWidth, -nHeight);
            // 菱形中画十字
            this.graphics.lineStyle(4, 0x000000);
            this.graphics.moveTo(_x + 9, _y + _height/2);
            this.graphics.lineTo(_x + _width - 9, _y + _height/2); // 画横线
            this.graphics.moveTo(_x + _width/2, _y + 9);
            this.graphics.lineTo(_x + _width/2, _y + _height - 9); // 画竖线
            // 画文件
            textField.x =  - _nWidth/2 - 8;
            textField.y = _nHeight/2;
            textField.width = _nWidth * 2.5;
            this.addChild(textField);
        }
    }
}
