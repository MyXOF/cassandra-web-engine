package com.corp.tsdb.cassandra.webengine.api.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-10T15:48:13.218+08:00")
public class ErrorConcise  {
  
  private Boolean success = null;
  private String reason = null;

  
  /**
   * success or not
   **/
  @JsonProperty("success")
  public Boolean getSuccess() {
    return success;
  }
  public void setSuccess(Boolean success) {
    this.success = success;
  }

  
  /**
   * reason why not success
   **/
  @JsonProperty("reason")
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorConcise {\n");
    
    sb.append("  success: ").append(success).append("\n");
    sb.append("  reason: ").append(reason).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
