package com.corp.tsdb.cassandra.webengine.api.rest.model;


import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-15T17:10:18.187+08:00")
public class PayloadSwitchOnOff  {

  private Boolean _switch = null;


  /**
   * true denotes On, and false denotes Off
   **/
  @JsonProperty("_switch")
  public Boolean getSwitch() {
    return _switch;
  }
  public void setSwitch(Boolean _switch) {
    this._switch = _switch;
  }



  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadSwitchOnOff {\n");

    sb.append("  _switch: ").append(_switch).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
