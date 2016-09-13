package com.dhian.daftarbelanja;

public class Data {
    public String sNamaBarang;
    public String sJumlah;
    public String sCheck;
    public String keyCheck;
    
    public Data(){
        
    }

    Data(String sNamaBarang, String sJumlah,String sCheck) {
        this.sNamaBarang = sNamaBarang;
        this.sJumlah = sJumlah;
        this.sCheck = sCheck;
      //  this.keyCheck = keyCheck;

    }
    
    @Override
    public boolean equals(Object object) {
        return object instanceof Data && sNamaBarang.equals(((Data) object).sNamaBarang);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

