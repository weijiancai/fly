/**
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.notation {
    public interface INotation {
        /**
         * 设置Notation Id值
         *
         * @param id Notation id值
         */
        function set nId(id:String):void;

        /**
         * 获取Notation id值
         */
        function get nId():String;

        /**
         * 设置Notation name值
         *
         * @param name Notation name值
         */
        function set nName(name:String):void;

        /**
         * 获取Notation name值
         */
        function get nName():String;

        /**
         * 绘制图形
         */
        function draw():void;
    }
}
