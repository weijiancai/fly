/**
 *
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    import com.fly.base.drawing.Pen;
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import flash.geom.Point;

    public class SequenceFlowNotation extends BaseNotation {
        private var fromNotation:BaseNotation;
        private var toNotation:BaseNotation;

        public function SequenceFlowNotation(fromNotation:BaseNotation, toNotation:BaseNotation) {
            this.fromNotation = fromNotation;
            this.toNotation = toNotation;

            super("", "", 0, 0, 0, 0);
        }


        override public function draw():void {
            // 计算开始节点图标的中心位置
            var fromCenterPoint:Point;
            if(fromNotation instanceof StartEventNotation || fromNotation instanceof EndEventNotation) {
                fromCenterPoint = new Point(fromNotation.nX, fromNotation.nY);
            } else {
                fromCenterPoint = new Point(fromNotation.nX + fromNotation.nWidth/2, fromNotation.nY + fromNotation.nHeight/2);
            }
            // 计算结束节点图标的中心位置
            var toCenterPoint:Point;
            if(toNotation instanceof StartEventNotation || toNotation instanceof EndEventNotation) {
                toCenterPoint = new Point(toNotation.nX, toNotation.nY);
            } else {
                toCenterPoint = new Point(toNotation.nX + toNotation.nWidth/2, toNotation.nY + toNotation.nHeight/2);
            }
            var fromPoint:Point = getLinkPoint(fromCenterPoint, toCenterPoint, fromNotation);
            var toPoint:Point = getLinkPoint(toCenterPoint, fromCenterPoint, toNotation);
            var pen:Pen = new Pen(graphics);
            pen.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
            NotationUtil.drawArrow(pen, fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
        }

        /**
         * 获取起始节点与图片边框的交点坐标，需按矩形对角线划分四个区域（此处为正方形，没90度为一个边界）
         *
         * @param fromPoint
         * @param targetPoint
         * @param acrossNode
         * @return
         */
        public static function getLinkPoint(fromPoint:Point, targetPoint:Point, acrossNode:BaseNotation):Point {
            var angle:Number = getAngle(fromPoint, targetPoint);
            // 计算交点与中心点的垂直和水平距离
            var distanceX:Number = Math.abs((targetPoint.x - fromPoint.x) * (acrossNode.width / 2) / (targetPoint.y - fromPoint.y));
            var distanceY:Number = Math.abs((targetPoint.y - fromPoint.y) * (acrossNode.width / 2) / (targetPoint.x - fromPoint.x));
            if(targetPoint.x < fromPoint.x) {
                distanceX = -distanceX;
            }
            if(targetPoint.y < fromPoint.y) {
                distanceY = -distanceY;
            }
            // 最终xy坐标
            var x:Number = 0;
            var y:Number = 0;
            if(angle >= 45 && angle < 135) {
                x = fromPoint.x + distanceX;
                y = acrossNode.y;
            } else if(angle >= 135 && angle < 225) {
                x = acrossNode.x;
                y = fromPoint.y + distanceY;
            } else if(angle > 225 && angle < 315) {
                x = fromPoint.x + distanceX;
                y = fromPoint.y + acrossNode.height/2;
            } else {
                x = fromPoint.x + acrossNode.width/2;
                y = fromPoint.y + distanceY;
            }
            return new Point(x, y);
        }

        // 获取两点夹角的角度
        private static function getAngle(fromPoint:Point, targetPoint:Point):Number {
            // 为了与三角坐标一致，y坐标的值要反过来
            // 通过反正切计算弧度
            var radian:Number = Math.abs(Math.atan((targetPoint.x - fromPoint.x) / (targetPoint.y - fromPoint.y)));
            // 计算角度
            var angle:Number = radian * 180 / Math.PI;
            if((targetPoint.x >= fromPoint.x) && (targetPoint.y <= fromPoint.y)) {  // 0 ~ 90区域
                angle = 90 - angle;
            } else if((targetPoint.x <= fromPoint.x) && (targetPoint.y <= fromPoint.y)) { // 90 ~ 180区域
                angle = 90 + angle;
            } else if((targetPoint.x <= fromPoint.x) && (targetPoint.y >= fromPoint.y)) { // 180 ~ 270区域
                angle = 270 - angle;
            } else if((targetPoint.x >= fromPoint.x) && (targetPoint.y >= fromPoint.y)) { // 270 ~ 360区域
                angle = 270 + angle;
            }
            return angle;
        }
    }
}
