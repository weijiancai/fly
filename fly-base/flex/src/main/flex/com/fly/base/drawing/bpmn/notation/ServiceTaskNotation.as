/**
 * 服务任务
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.ProcessPng;

    public class ServiceTaskNotation extends BaseNotation {
        public function ServiceTaskNotation(id:String, name:String, x:Number, y:Number, width:Number, height:Number) {
            super(id, name, x, y, width, height);
        }


        override public function draw():void {
            // 设置图片
            this.source = ProcessPng.SERVICE_TASK;
            // 画圆角矩形
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawRoundRect(-0.5, -0.5, _nWidth, _nHeight, _eclipseWidth, _eclipseHeight);
            graphics.endFill();
        }
    }
}
