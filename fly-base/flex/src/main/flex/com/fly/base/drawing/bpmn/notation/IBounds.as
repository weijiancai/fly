/**
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    public interface IBounds {
        /**
         * 设置x坐标值
         *
         * @param x x坐标值
         */
        function set nX(x:Number):void;

        /**
         * 获取x坐标值
         */
        function get nX():Number;

        /**
         * 设置y坐标值
         *
         * @param y y坐标值
         */
        function set nY(y:Number):void;

        /**
         * 获取y坐标值
         */
        function get nY():Number;

        /**
         * 设置宽度
         *
         * @param width 宽度
         */
        function set nWidth(width:Number):void;

        /**
         * 获取宽度
         */
        function get nWidth():Number;

        /**
         * 设置高度
         *
         * @param height 高度
         */
        function set nHeight(height:Number):void;

        /**
         * 获取高度
         */
        function get nHeight():Number;
    }
}
