package com.example.DomainQLNS.Config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    ADMIN(1),
    HR(2),
    USER(3);
    public final long value; // Biến này lưu giá trị số tương ứng với mỗi vai trò.
}
