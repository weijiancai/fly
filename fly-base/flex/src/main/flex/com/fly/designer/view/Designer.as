/**
 *
 * @author weijiancai
 */
package com.fly.designer.view {
    import mx.containers.Panel;

    public class Designer extends Panel {
        public function Designer() {
            super();
            this.percentWidth = 100;
            this.percentHeight = 100;
            this.addChild(new BPMNEditor());
        }
    }
}
