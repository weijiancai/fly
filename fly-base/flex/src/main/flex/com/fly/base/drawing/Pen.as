/**
 * 绘图用的画笔
 *
 * @author weijiancai
 */
package com.fly.base.drawing {
    import flash.display.BitmapData;
    import flash.display.Graphics;
    import flash.geom.Matrix;
    import flash.geom.Point;

    public class Pen {
        private var _graphics:Graphics;
        private var _lineStyleSet:Boolean; // 是否设置了线条样式
        private var _currentX:Number = 0; // 当前X坐标
        private var _currentY:Number = 0; // 当前Y坐标

        public function Pen(graphics:Graphics) {
            _graphics = graphics;
        }

        /**
         * 指定一种线条样式，Flash 可将该样式用于随后调用对象的其它 Graphics 方法（如 lineTo() 或 drawCircle()）。
         *
         * @param thickness 定义线条的宽度，默认值为1，范围为0到255。如果指定的值超出它会自动工调整最接近的合法数值。
         * @param color 线条的颜色，默认为0x000000
         * @param alpha 线条的透明度，范围为0到1，默认为1
         * @param pixelHinting 布尔型，指示线条是否包住整个像素，默认为false
         * @param scaleMode flash.display.LineScaleMode 中的一个常量， 有NORMAL ( 默认), NONE, VERTICAL, 和 HORIZONTAL。当值为NORMAL，随着所在对象的缩放而缩放。比如，如果有个含有1像素的
                             线条的sprite被缩放到200%，那么线条的宽度也缩放到2个像素。如果设置为NONE，线条不会被缩放。如果设置为VERTICAL，则只在垂直方向所放，如果设为HORIZONTAL，则在水平方向所放。
         * @param caps 指定线条末端的封盖，它是flash.display.CapsStyle 的常量，有NONE, ROUND (默认)，和SQUARE.
         * @param joints 当连接线段时所指定的类型，它是flash.display.JointStyle 的常量，有BEVEL, MITER, 和ROUND(默认).
         * @param miterLimit 当连接类型为MITER 时还要指定斜接限制。默认为3，范围从1到255
         */
        public function lineStyle(thickness:Number = 1, color:uint = 0, alpha:Number = 1.0, pixelHinting:Boolean = false, scaleMode:String = "normal", caps:String = null, joints:String = null, miterLimit:Number = 3):void {
            _graphics.lineStyle(thickness, color, alpha, pixelHinting, scaleMode, caps, joints, miterLimit);
            _lineStyleSet = true;
        }

        /**
         * 指定一种线条样式的渐变，该渐变将在随后对其他 Graphics 方法（如 lineTo() 或 drawCircle()）的调用中用于进行绘制。
         *
         * @param type
         * @param colors
         * @param alphas
         * @param ratios
         * @param matrix
         * @param spreadMethod
         * @param interpolationMethod
         * @param focalPointRatio
         */
        public function lineGradientStyle(type:String, colors:Array, alphas:Array, ratios:Array, matrix:Matrix = null, spreadMethod:String = "pad", interpolationMethod:String = "rgb", focalPointRatio:Number = 0):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.lineGradientStyle(type, colors, alphas, ratios, matrix, spreadMethod, interpolationMethod, focalPointRatio);
        }

        /**
         * 使用当前线条样式绘制一条从当前绘画位置开始到 (x, y) 结束的直线；当前绘画位置随后会设置为 (x, y)。
         *
         * @param x
         * @param y
         */
        public function lineTo(x:Number, y:Number):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.lineTo(x, y);
            currentPosition(x, y);
        }

        /**
         * 将当前绘画位置移动到 (x, y)。
         *
         * @param x
         * @param y
         */
        public function moveTo(x:Number, y:Number):void {
            _graphics.moveTo(x, y);
            currentPosition(x, y);
        }

        /**
         * 使用当前线条样式和由 (controlX, controlY) 指定的控制点绘制一条从当前绘画位置开始到 (anchorX, anchorY) 结束的曲线。
         *
         * @param controlX
         * @param controlY
         * @param anchorX
         * @param anchorY
         */
        public function curveTo(controlX:Number, controlY:Number, anchorX:Number, anchorY:Number):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.curveTo(controlX, controlY, anchorX, anchorY);
            currentPosition(anchorX, anchorY);
        }

        /**
         * 设置当前坐标点位置
         *
         * @param x
         * @param y
         */
        public function currentPosition(x:Number, y:Number):void {
            _currentX = x;
            _currentY = y;
        }

        /**
         * 用位图图像填充绘图区。
         *
         * @param bitmap 包含要显示的位的透明或不透明位图图像。
         * @param matrix 一个 matrix 对象（属于 flash.geom.Matrix 类），您可以使用它在位图上定义转换。
         * @param repeat 如果为 true，则位图图像按平铺模式重复。如果为 false，位图图像不会重复，并且位图边缘将用于所有扩展出位图的填充区域。
         * @param smooth 如果为 false，则使用最近邻点算法来呈现放大的位图图像，而且该图像看起来是像素化的。如果为 true，则使用双线性算法来呈现放大的位图图像。使用最近邻点算法呈现通常较快。
         */
        public function beginBitmapFill(bitmap:BitmapData, matrix:Matrix = null, repeat:Boolean = true, smooth:Boolean = false):void {
            _graphics.beginBitmapFill(bitmap, matrix, repeat, smooth);
        }

        /**
         * 指定一种简单的单一颜色填充，在绘制时该填充将在随后对其他 Graphics 方法（如 lineTo() 或 drawCircle()）的调用中使用。
         *
         * @param color 填充的颜色 (0xRRGGBB)。
         * @param alpha 填充的 Alpha 值（从 0.0 到 1.0）。如果alpha为0，就不会填充图形了.
         */
        public function beginFill(color:uint, alpha:Number = 1.0):void {
            _graphics.beginFill(color, alpha);
        }

        /**
         * 指定一种渐变填充，在绘制时该填充将在随后对其他 Graphics 方法（如 lineTo() 或 drawCircle()）的调用中使用。
         *
         * @param type 用于指定要使用哪种渐变类型的 GradientType 类的值：GradientType.LINEAR 或 GradientType.RADIAL。
         * @param colors 要在渐变中使用的 RGB 十六进制颜色值数组（例如，红色为 0xFF0000，蓝色为 0x0000FF，等等）。可以至多指定 15 种颜色。对于每种颜色，请确保在 alphas 和 ratios 参数中指定对应的值。
         * @param alphas colors 数组中对应颜色的 alpha 值数组；有效值为 0 到 1。如果值小于 0，则默认值为 0。如果值大于 1，则默认值为 1。
         * @param ratios 颜色分布比例的数组；有效值为 0 到 255。该值定义 100% 采样的颜色所在位置的宽度百分比。值 0 表示渐变框中的左侧位置，255 表示渐变框中的右侧位置。
         * @param matrix 一个由 flash.geom.Matrix 类定义的转换矩阵。flash.geom.Matrix 类包括 createGradientBox() 方法，通过该方法可以方便地设置矩阵，以便与 beginGradientFill() 方法一起使用。
         * @param spreadMethod 用于指定要使用哪种 spread 方法的 SpreadMethod 类的值：SpreadMethod.PAD、SpreadMethod.REFLECT 或 SpreadMethod.REPEAT。
         * @param interpolationMethod 用于指定要使用哪个值的 InterpolationMethod 类的值：InterpolationMethod.linearRGB 或 InterpolationMethod.RGB
         * @param focalPointRatio 一个控制渐变的焦点位置的数字。0 表示焦点位于中心。1 表示焦点位于渐变圆的一条边界上。-1 表示焦点位于渐变圆的另一条边界上。小于 -1 或大于 1 的值将舍入为 -1 或 1。
         */
        public function beginGradientFill(type:String, colors:Array, alphas:Array, ratios:Array, matrix:Matrix = null, spreadMethod:String = "pad", interpolationMethod:String = "rgb", focalPointRatio:Number = 0):void {
            _graphics.beginGradientFill(type, colors, alphas, ratios, matrix, spreadMethod, interpolationMethod, focalPointRatio);
        }

        /**
         * 对从上一次调用 beginFill()、beginGradientFill() 或 beginBitmapFill() 方法之后添加的直线和曲线应用填充。
         */
        public function endFill():void {
            _graphics.endFill();
        }

        /**
         * 清除绘制到此 Graphics 对象的图形，并重置填充和线条样式设置。
         */
        public function clear():void {
            _graphics.clear();
            _lineStyleSet = false;
        }

        /**
         * 从开始点到结束点绘制一条直线
         *
         * @param startX
         * @param startY
         * @param endX
         * @param endY
         */
        public function drawLine(startX:Number, startY:Number, endX:Number, endY:Number):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.moveTo(startX, startY);
            _graphics.lineTo(endX, endY);
        }

        /**
         * 从开始点到结束点绘制一条虚线
         *
         * @param startPoint 开始点
         * @param endPoint 结束点
         * @param solidLength 虚线中实线的长度
         * @param brokenLength 虚线中空白的长度
         */
        public function dottedLine(startPoint:Point, endPoint:Point, solidLength:Number = 10, brokenLength:Number = 5):void {
            var lineAngle:Number = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);  // 线角度

            var xSolidLength:Number = solidLength * Math.cos(lineAngle);
            var ySolidLength:Number = solidLength * Math.sin(lineAngle);

            var xBrokenLength:Number = brokenLength * Math.cos(lineAngle);
            var yBrokenLength:Number = brokenLength * Math.sin(lineAngle);

            var tempP:Point = new Point(startPoint.x, startPoint.y);
            var tempToP:Point = new Point(endPoint.x, endPoint.y);
            var lineType:String = "solid";

            while (startPoint.x != endPoint.x ? (tempP.x < endPoint.x) : (tempP.x > endPoint.x)) { // 判断是否还在开始点和结束点之间
                if (lineType == "solid") {
                    tempToP.x = tempP.x + xSolidLength;
                    tempToP.y = tempP.y + ySolidLength;
                    this.moveTo(tempP.x, tempP.y);
                    this.lineTo(tempToP.x, tempToP.y);
                    lineType = "space";
                } else {
                    tempToP.x = tempP.x + xBrokenLength;
                    tempToP.y = tempP.y + yBrokenLength;
                    this.moveTo(tempToP.x, tempToP.y);
                    lineType = "solid";
                }
                tempP.x = tempToP.x;
                tempP.y = tempToP.y;
            }
        }

        /**
         * 绘制一个圆。
         *
         * @param x 圆中心的x坐标
         * @param y 圆中心的y坐标
         * @param radius 圆半径
         */
        public function drawCircle(x:Number, y:Number, radius:Number):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.drawCircle(x, y, radius);
        }

        /**
         * 绘制一个椭圆。
         *
         * @param x
         * @param y
         * @param width
         * @param height
         */
        public function drawEllipse(x:Number, y:Number, width:Number, height:Number):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.drawEllipse(x, y, width, height);
        }

        /**
         * 绘制一个矩形。
         *
         * @param x
         * @param y
         * @param width
         * @param height
         */
        public function drawRect(x:Number, y:Number, width:Number, height:Number):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.drawRect(x, y, width, height);
        }

        /**
         * 绘制一个圆角矩形。
         *
         * @param x
         * @param y
         * @param width
         * @param height
         * @param ellipseWidth
         * @param ellipseHeight
         */
        public function drawRoundRect(x:Number, y:Number, width:Number, height:Number, ellipseWidth:Number, ellipseHeight:Number = NaN):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            _graphics.drawRoundRect(x + 0.5, y + 0.5, width, height, ellipseWidth, ellipseHeight);  // + 0.5 抗锯齿
        }

        /**
         * 绘制一个圆角矩形。可以指定每个圆角的半径
         *
         * @param x
         * @param y
         * @param width
         * @param height
         * @param a
         * @param b
         * @param c
         * @param d
         */
        public function drawRoundRectComplex(x:Number, y:Number, width:Number, height:Number, a:Number, b:Number, c:Number, d:Number):void {
            if (!_lineStyleSet) {
                lineStyle();
            }
            _graphics.drawRoundRectComplex(x + 0.5, y + 0.5, width, height, a, b, c, d);  // + 0.5 抗锯齿
        }

        /**
         * 绘制一个扇形（弧线）
         *
         * @param x 扇形中心的x坐标（圆的中心）
         * @param y 扇形中心的y坐标
         * @param radius 扇形半径
         * @param arc 扇形度数，指定为角度
         * @param startingAngle 扇形开始角度，默认为0
         * @param radialLines 布尔值，指示是否画出扇形两端点到中心的直线，默认为false
         */
        public function drawArc(x:Number, y:Number, radius:Number, arc:Number, startingAngle:Number = 0, radialLines:Boolean = false):void {
            // The angle of each of the eight segments is 45 degrees (360 divided by eight),
            // which equals p/4 radians.
            if (arc > 360) {
                arc = 360;
            }
            arc = Math.PI / 180 * arc;
            var nAngleDelta:Number = arc / 8;

            // Find the distance from the circle's center to the control points
            // for the curves.
            var nCtrlDist:Number = radius / Math.cos(nAngleDelta / 2);

            startingAngle *= Math.PI / 180;

            var nAngle:Number = startingAngle;
            var nCtrlX:Number;
            var nCtrlY:Number;
            var nAnchorX:Number;
            var nAnchorY:Number;

            var nStartingX:Number = x + Math.cos(startingAngle) * radius;
            var nStartingY:Number = y + Math.sin(startingAngle) * radius;

            if (radialLines) {
                moveTo(x, y);
                lineTo(nStartingX, nStartingY);
            }
            else {
                // Move to the starting point, one radius to the right of the circle's center.
                moveTo(nStartingX, nStartingY);
            }

            // Repeat eight times to create eight segments.
            for (var i:Number = 0; i < 8; i++) {
                // Increment the angle by angleDelta (p/4) to create the whole circle (2p).
                nAngle += nAngleDelta;

                // The control points are derived using sine and cosine.
                nCtrlX = x + Math.cos(nAngle - (nAngleDelta / 2)) * (nCtrlDist);
                nCtrlY = y + Math.sin(nAngle - (nAngleDelta / 2)) * (nCtrlDist);

                // The anchor points (end points of the curve) can be found similarly to the
                // control points.
                nAnchorX = x + Math.cos(nAngle) * radius;
                nAnchorY = y + Math.sin(nAngle) * radius;

                // Draw the segment.
                curveTo(nCtrlX, nCtrlY, nAnchorX, nAnchorY);
            }
            if (radialLines) {
                lineTo(x, y);
            }
        }

        /**
         * 绘制一个三角形, 给出两条边的长度和夹角就能确定一个三角形.
         *
         * @param x 夹角点的x坐标
         * @param y 夹角点的y坐标
         * @param ab a（夹角点）到b的长度
         * @param ac a（夹角点）到c的长度
         * @param angle ab与ac的夹角
         * @param rotation 三角形的旋转角度。如果是0或undefined，那么ac平行于x轴。
         */
        public function drawTriangle(x:Number, y:Number, ab:Number, ac:Number, angle:Number, rotation:Number = 0):void {
            rotation = rotation * Math.PI / 180;

            // Convert the angle between the sides from degrees to radians.
            angle = angle * Math.PI / 180;

            // Calculate the coordinates of points b and c.
            var nBx:Number = Math.cos(angle - rotation) * ab;
            var nBy:Number = Math.sin(angle - rotation) * ab;
            var nCx:Number = Math.cos(-rotation) * ac;
            var nCy:Number = Math.sin(-rotation) * ac;

            // Calculate the centroid's coordinates.
            var nCentroidX:Number = 0;
            var nCentroidY:Number = 0;

            // Move to point a, then draw line ac, then line cb, and finally ba (ab).
            drawLine(-nCentroidX + x, -nCentroidY + y, nCx - nCentroidX + x, nCy - nCentroidY + y);
            lineTo(nBx - nCentroidX + x, nBy - nCentroidY + y);
            lineTo(-nCentroidX + x, -nCentroidY + y);
        }

        /**
         * 绘制一个规则的多边形（所有的边相等）
         *
         * @param x 多边形中心的x坐标
         * @param y 多边形中心的y坐标
         * @param sides 多边形边数
         * @param length 边长度
         * @param rotation 旋转角度
         */
        public function drawRegularPolygon(x:Number, y:Number, sides:Number, length:Number, rotation:Number = 0):void {
            rotation = rotation * Math.PI / 180;

            // The angle formed between the segments from the polygon's center as shown in
            // Figure 4-5. Since the total angle in the center is 360 degrees (2p radians),
            // each segment's angle is 2p divided by the number of sides.
            var nAngle:Number = (2 * Math.PI) / sides;

            // Calculate the length of the radius that circumscribes the polygon (which is
            // also the distance from the center to any of the vertices).
            var nRadius:Number = (length / 2) / Math.sin(nAngle / 2);

            // The starting point of the polygon is calculated using trigonometry where
            // radius is the hypotenuse and nRotation is the angle.
            var nPx:Number = (Math.cos(rotation) * nRadius) + x;
            var nPy:Number = (Math.sin(rotation) * nRadius) + y;

            // Move to the starting point without yet drawing a line.
            moveTo(nPx, nPy);

            // Draw each side. Calculate the vertex coordinates using the same trigonometric
            // ratios used to calculate px and py earlier.
            for (var i:Number = 1; i <= sides; i++) {
                nPx = (Math.cos((nAngle * i) + rotation) * nRadius) + x;
                nPy = (Math.sin((nAngle * i) + rotation) * nRadius) + y;
                lineTo(nPx, nPy);
            }
        }

        /**
         * 绘制一个星形
         *
         * @param x 星形中心的x坐标
         * @param y 星形中心的y坐标
         * @param points 星形的顶点数
         * @param innerRadius 内半径
         * @param outerRadius 外半径
         * @param rotation 旋转角度，默认为0
         */
        public function drawStar(x:Number, y:Number, points:Number, innerRadius:Number, outerRadius:Number, rotation:Number = 0):void {
            if (points < 3) {
                return;
            }

            var nAngleDelta:Number = (Math.PI * 2) / points;
            rotation = Math.PI * (rotation - 90) / 180;

            var nAngle:Number = rotation;

            var nPenX:Number = x + Math.cos(nAngle + nAngleDelta / 2) * innerRadius;
            var nPenY:Number = y + Math.sin(nAngle + nAngleDelta / 2) * innerRadius;

            moveTo(nPenX, nPenY);

            nAngle += nAngleDelta;

            for (var i:Number = 0; i < points; i++) {
                nPenX = x + Math.cos(nAngle) * outerRadius;
                nPenY = y + Math.sin(nAngle) * outerRadius;
                lineTo(nPenX, nPenY);
                nPenX = x + Math.cos(nAngle + nAngleDelta / 2) * innerRadius;
                nPenY = y + Math.sin(nAngle + nAngleDelta / 2) * innerRadius;
                lineTo(nPenX, nPenY);
                nAngle += nAngleDelta;
            }
        }

        /**
         * 绘制一个菱形（四边形）
         *
         * @param x 菱形的x坐标
         * @param y 菱形的x坐标
         * @param width 菱形的宽
         * @param height 菱形的高
         */
        public function drawRhombus(x:Number, y:Number, width:Number, height:Number):void {
            if(!_lineStyleSet) {
                lineStyle();
            }
            moveTo(x, y + height / 2);
            lineTo(x + width / 2, y);
            lineTo(x + width, y + height / 2);
            lineTo(x + width / 2, y + height);
            lineTo(x, y + height / 2);
        }
    }
}
