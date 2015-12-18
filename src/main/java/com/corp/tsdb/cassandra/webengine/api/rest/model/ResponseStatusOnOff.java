package com.corp.tsdb.cassandra.webengine.api.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-15T17:10:18.187+08:00")
public class ResponseStatusOnOff  {

  private Boolean sucess = null;
  private Boolean status = null;


  /**
   **/
  @JsonProperty("sucess")
  @ApiModelProperty(value = "")
  public Boolean getSucess() {
    return sucess;
  }
  public void setSucess(Boolean sucess) {
    this.sucess = sucess;
  }


  /**
   * true denotes On, and false denotes Off
   **/
  @JsonProperty("status")
  @ApiModelProperty(value = "")
  public Boolean getStatus() {
    return status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
  }



  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseStatusOnOff {\n");

    sb.append("  sucess: ").append(sucess).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
