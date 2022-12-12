package com.wes.project_1918083_tokopakaian;

public class PER8Model {
    private int id;
    private byte[]proavatar;
    private String username;
    private String usernomer;
    //constructor
    public PER8Model(int id, byte[] proavatar, String username,
                 String usernomer) {
        this.id = id;
        this.proavatar = proavatar;
        this.username = username;
        this.usernomer = usernomer;
    }
    //getter and setter method
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public byte[] getProavatar() {
        return proavatar;
    }
    public void setProavatar(byte[] proavatar) {
        this.proavatar = proavatar;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsernomer() {
        return usernomer;
    }
    public void setUsernomer(String usernomer) {
        this.usernomer = usernomer;
    }
}