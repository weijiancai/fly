/**
 * 流程所用的PNG图片
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    public class ProcessPng {
        [Bindable]
        [Embed(source="./images/annotation.png")]
        public static var ANNOTATION:Class;

        [Bindable]
        [Embed(source="./images/association.png")]
        public static var ASSOCIATION:Class;

        [Bindable]
        [Embed(source="./images/business_rule.png")]
        public static var BUSINESS_RULE:Class;  // 业务规则

        [Bindable]
        [Embed(source="./images/callactivity.png")]
        public static var CALL_ACTIVITY:Class;

        [Bindable]
        [Embed(source="./images/endevent_none.png")]
        public static var END_EVENT_NONE:Class;

        [Bindable]
        [Embed(source="./images/error.png")]
        public static var ERROR:Class;

        [Bindable]
        [Embed(source="./images/error_catch.png")]
        public static var ERROR_CATCH:Class;  // 错误捕获

        [Bindable]
        [Embed(source="./images/error_throw.png")]
        public static var ERROR_THROW:Class;  // 错误抛出

        [Bindable]
        [Embed(source="./images/event_subprocess.png")]
        public static var EVENT_SUB_PROCESS:Class;

        [Bindable]
        [Embed(source="./images/gateway_event.png")]
        public static var GATEWAY_EVENT:Class;

        [Bindable]
        [Embed(source="./images/gateway_exclusive.png")]
        public static var GATEWAY_EXCLUSIVE:Class;

        [Bindable]
        [Embed(source="./images/gateway_inclusive.png")]
        public static var GATEWAY_INCLUSIVE:Class;

        [Bindable]
        [Embed(source="./images/gateway_parallel.png")]
        public static var GATEWAY_PARALLEL:Class;

        [Bindable]
        [Embed(source="./images/lane.png")]
        public static var LANE:Class;

        [Bindable]
        [Embed(source="./images/letter.png")]
        public static var LETTER:Class;

        [Bindable]
        [Embed(source="./images/loopmarker.png")]
        public static var LOOP_MARKER:Class;

        [Bindable]
        [Embed(source="./images/mail.png")]
        public static var MAIL:Class;

        [Bindable]
        [Embed(source="./images/manual.png")]
        public static var MANUAL_TASK:Class;  // 手动任务

        [Bindable]
        [Embed(source="./images/pool.png")]
        public static var POOL:Class;

        [Bindable]
        [Embed(source="./images/receive.png")]
        public static var RECEIVE_TASK:Class;  // 接收任务

        [Bindable]
        [Embed(source="./images/script.png")]
        public static var SCRIPT_TASK:Class;  // 脚本任务

        [Bindable]
        [Embed(source="./images/send.png")]
        public static var SEND_TASK:Class;  // 发送任务

        [Bindable]
        [Embed(source="./images/sequence_flow.gif")]
        public static var SEQUENCE_FLOW:Class;

        [Bindable]
        [Embed(source="./images/service.png")]
        public static var SERVICE_TASK:Class;  // 服务任务

        [Bindable]
        [Embed(source="./images/signal.png")]
        public static var SIGNAL:Class;

        [Bindable]
        [Embed(source="./images/signal_catch.png")]
        public static var SIGNAL_CATCH:Class;  // 信号捕获

        [Bindable]
        [Embed(source="./images/signal_throw.png")]
        public static var SIGNAL_THROW:Class;  // 信号抛出

        [Bindable]
        [Embed(source="./images/startevent_message.png")]
        public static var START_EVENT_MESSAGE:Class;

        [Bindable]
        [Embed(source="./images/startevent_none.png")]
        public static var START_EVENT_NONE:Class;

        [Bindable]
        [Embed(source="./images/subprocess_collapsed.png")]
        public static var SUB_PROCESS_COLLAPSED:Class;

        [Bindable]
        [Embed(source="./images/subprocess_expanded.png")]
        public static var SUB_PROCESS_EXPANDED:Class;

        [Bindable]
        [Embed(source="./images/textannotation.png")]
        public static var TEXT_ANNOTATION:Class;

        [Bindable]
        [Embed(source="./images/throw_none.png")]
        public static var THROW_NONE:Class;

        [Bindable]
        [Embed(source="./images/throw_signal.png")]
        public static var THROW_SIGNAL:Class;

        [Bindable]
        [Embed(source="./images/timer.png")]
        public static var TIMER_TASK:Class;  // 定时器任务

        [Bindable]
        [Embed(source="images/user_icon.png")]
        public static var USER_ICON:Class;  // 用户任务图标

        [Bindable]
        [Embed(source="images/user.png")]
        public static var USER_TASK:Class;  // 用户任务
    }
}
