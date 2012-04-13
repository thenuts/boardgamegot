package nutis.client.dto;

import java.io.Serializable;


public class KeyDto implements Serializable {
  
  private static final long serialVersionUID = 4991075764915324218L;
  
  private String kind;
  private long id;
  
//  public KeyDTO(Key id) {
//    this.id = id.getId();
//    this.kind = id.getKind();
//  }

  public String getKind() {
    return kind;
  }
  
  public void setKind(String kind) {
    this.kind = kind;
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
}
