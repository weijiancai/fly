package org.puremvc.java.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: wei_jc
 * Date: 13-4-13
 * Time: 上午7:35
 * To change this template use File | Settings | File Templates.
 */
public interface INotification {
    public String getName();

    public void setBody(Object body);

    public Object getBody();

    public void setType(String type);

    public String getType();

    public String toString();
}
