/**
 * 用户任务
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.bpmn.ProcessPng;

    public class UserTaskNotation extends BaseNotation {
        public function UserTaskNotation(id:String, name:String, x:Number, y:Number, width:Number, height:Number) {
            super(id, name, x, y, width, height);
        }


        override public function draw():void {
            // 设置图片
            this.source = ProcessPng.USER_TASK;
            // 画圆角矩形
            graphics.lineStyle(_thickness, _color, 1, true);
            graphics.beginFill(_fillColor, 0.4);
            graphics.drawRoundRect(-2.5, -1.5, _nWidth, _nHeight, _eclipseWidth, _eclipseHeight);
            graphics.endFill();
            // 画文件
            textField.x =  10;
            textField.y = 6;
            this.addChild(textField);
        }
    }
}
