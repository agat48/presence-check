package pl.agat.app;

public class Member {
     
    //private variables
    int _id;
    String _name;
    String _surname;
    int _voice;
    String _phone_number;
     
    // Empty constructor
    public Member(){
         this._name=new String("Ala");
         this._surname=new String("Makota");
         this._voice=2;
         this._phone_number=new String("123456789");
    }
    // constructor
    public Member(int id, String name, String surname, int voice, String _phone_number){
        this._id = id;
        this._name = name;
        this._surname = surname;
        this._voice = voice;
        this._phone_number = _phone_number;
    }
     
    // constructor
    public Member(String name, String surname, int voice){
        this._name = name;
        this._surname = surname;
        this._voice = voice;
    }
    public Member(String name, String surname, int voice, String _phone_number){
        this._name = name;
        this._surname = surname;
        this._voice = voice;
        this._phone_number = _phone_number;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getName(){
        return this._name;
    }
     
    // setting name
    public void setName(String name){
        this._name = name;
    }
     
 // getting surname
    public String getSurname(){
        return this._surname;
    }
     
    // setting name
    public void setSurname(String surname){
        this._surname = surname;
    }
    
 // getting voiceID
    public int getVoice(){
        return this._voice;
    }
     
    // setting name
    public void setVoice(int voice){
        this._voice = voice;
    }
    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }
     
    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}