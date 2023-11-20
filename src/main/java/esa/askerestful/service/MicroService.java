package esa.askerestful.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MicroService {

    private int currentId = 0;
    private int currentIdPertanyaan = 0;
    private int currentIdToken = 0;

    private int currentIdGambar;

    private int currentIdKomentar = 0;
    public String idUserGenerator(){
        String userId = "USER_" + String.format("%04d" , currentId);
        currentId++;
        return userId;
    }

    public String idPertanyaanGenerator(){
        String pertanyaanId = "prt_" + String.format("%04d" , currentIdPertanyaan);
        currentIdPertanyaan++;
        return pertanyaanId;
    }
    public String idKomentarGenerator(){
        String komentarId = "KOMEN_" + String.format("%04d" , currentIdKomentar);
        currentIdKomentar++;
        return komentarId;
    }

    public String tokenGenerator(){
        String tokenId = "TOKEN_" + String.format("%04d" , currentIdToken);
        currentIdToken++;
        return tokenId;
    }
    public String idGambarGenerator(){
        String gambarId = "Gambar_" + String.format("%04d" , currentIdGambar);
        currentIdGambar++;
        return gambarId;
    }

    public Long next30Days(){
        return System.currentTimeMillis() + (1000 * 16 * 24 *30);
    }

    public Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

}
