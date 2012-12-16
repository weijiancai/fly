/**
 * 流程图标
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import mx.controls.Image;

    public class ProcessIcon {
        public static var ANNOTATION:Image = new Image();

        public static var ASSOCIATION:Image = new Image();

        public static var BUSINESS_RULE:Image = new Image();  // 业务规则

        public static var CALL_ACTIVITY:Image = new Image();

        public static var END_EVENT_NONE:Image = new Image();

        public static var ERROR:Image = new Image();

        public static var ERROR_CATCH:Image = new Image();  // 错误捕获

        public static var ERROR_THROW:Image = new Image();  // 错误抛出

        public static var EVENT_SUB_PROCESS:Image = new Image();

        public static var GATEWAY_EVENT:Image = new Image();

        public static var GATEWAY_EXCLUSIVE:Image = new Image();

        public static var GATEWAY_INCLUSIVE:Image = new Image();

        public static var GATEWAY_PARALLEL:Image = new Image();

        public static var LANE:Image = new Image();

        public static var LETTER:Image = new Image();

        public static var LOOP_MARKER:Image = new Image();

        public static var MAIL:Image = new Image();

        public static var MANUAL_TASK:Image = new Image();  // 手动任务

        public static var POOL:Image = new Image();

        public static var RECEIVE_TASK:Image = new Image();  // 接收任务

        public static var SCRIPT_TASK:Image = new Image();  // 脚本任务

        public static var SEND_TASK:Image = new Image();  // 发送任务

        public static var SEQUENCE_FLOW:Image = new Image();

        public static var SERVICE_TASK:Image = new Image();  // 服务任务

        public static var SIGNAL:Image = new Image();

        public static var SIGNAL_CATCH:Image = new Image();  // 信号捕获

        public static var SIGNAL_THROW:Image = new Image();  // 信号抛出

        public static var START_EVENT_MESSAGE:Image = new Image();

        public static var START_EVENT_NONE:Image = new Image();

        public static var SUB_PROCESS_COLLAPSED:Image = new Image();

        public static var SUB_PROCESS_EXPANDED:Image = new Image();

        public static var TEXT_ANNOTATION:Image = new Image();

        public static var THROW_NONE:Image = new Image();

        public static var THROW_SIGNAL:Image = new Image();

        public static var TIMER_TASK:Image = new Image();  // 定时器任务

        public static var USER_TASK:Image = new Image();  // 用户任务

        static {
            ANNOTATION.source = ProcessPng.ANNOTATION;
        }
        public function ProcessIcon() {
        }
    }
}
