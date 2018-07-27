package com.enn.DTO;

/**
 * 外部user
 */
public class ExUser {
        private int uid;

        private String phone;

        private String nickName;

        public int getUid()
        {
            return uid;
        }

        public void setUid(int uid)
        {
            this.uid = uid;
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }

        public String getNickName()
        {
            return nickName;
        }

        public void setNickName(String nickName)
        {
            this.nickName = nickName;
        }

}
