package br.com.alura.conversormoedas.modelos;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

public class Moeda implements Comparable<Moeda> {

    /*
    "result": "success",
	"documentation": "https://www.exchangerate-api.com/docs",
	"terms_of_use": "https://www.exchangerate-api.com/terms",
	"time_last_update_unix": 1585267200,
	"time_last_update_utc": "Fri, 27 Mar 2020 00:00:00 +0000",
	"time_next_update_unix": 1585270800,
	"time_next_update_utc": "Sat, 28 Mar 2020 01:00:00 +0000",
	"base_code": "EUR",
	"target_code": "GBP",
	"conversion_rate": 0.8412
	*/

        @SerializedName("result")
        private String result;

        @SerializedName("documentation")
        private String documentation;

        @SerializedName("terms_of_use")
        private String terms_of_use;

        @SerializedName("time_last_update_unix")
        private int time_last_update_unix;

        @SerializedName("time_last_update_utc")
        private Date time_last_update_utc;

        @SerializedName("time_next_update_unix")
        private int time_next_update_unix;

        @SerializedName("time_next_update_utc")
        private Date time_next_update_utc;

        @SerializedName("base_code")
        private String base_code;

        @SerializedName("target_code")
        private String target_code;

        @SerializedName("conversion_rate")
        private double conversion_rate;

        private double valor;
        private String nome;


        public double getValor() {
                return valor;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }


        public String getBase_code() {
                return base_code;
        }

        public String getTarget_code() {
                return target_code;
        }


    public String getResult() {
        return result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getTerms_of_use() {
        return terms_of_use;
    }

    public int getTime_last_update_unix() {
        return time_last_update_unix;
    }

    public Date getTime_last_update_utc() {
        return time_last_update_utc;
    }

    public int getTime_next_update_unix() {
        return time_next_update_unix;
    }

    public Date getTime_next_update_utc() {
        return time_next_update_utc;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public double getConversion_rate() {
        return conversion_rate;
    }

        @Override
        public int compareTo(Moeda o) {
                return 0;
        }
}







