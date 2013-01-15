/**
 * 服务任务
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.ProcessPng;

    public class ServiceTaskNotation extends BaseNotation {
        public function ServiceTaskNotation(name:String, x:Number, y:Number, width:Number, height:Number) {
            super(name, x, y, width, height);
        }


        override public function draw():void {
            // 设置图片
            this.source = ProcessPng.SERVICE_TASK;
            // 设置坐标点
            this.x = _nX - _nWidth/2 - 5;
            this.y = _nY - _nHeight/2 - 5;
            // 画圆角矩形
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawRoundRect(-3.5, -2.5, _nWidth, _nHeight, _eclipseWidth, _eclipseHeight);
            graphics.endFill();
            // 画文件
            textField.x =  13;
            textField.y = 9;
            this.addChild(textField);
        }
    }
}
