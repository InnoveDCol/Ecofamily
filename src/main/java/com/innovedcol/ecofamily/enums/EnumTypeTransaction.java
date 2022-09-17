package com.innovedcol.ecofamily.enums;

import javax.persistence.Table;

@Table(name = "ENUM_TYPE_TRANSACTION")
public enum EnumTypeTransaction {
    Consignacion,
    Retiro,
    Ajuste
}
