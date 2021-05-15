-- liquibase formatted sql

-- changeset 20210515.[BE-17]-update-passwords

UPDATE administrator SET login ='superadmin' WHERE id =1@@
UPDATE administrator SET password ='$2a$10$D..lAztDm77NWQ5WUzfazOOWmthX3hUJm/I.odO./YbaKj2meEi7q' WHERE id =1@@
UPDATE administrator SET password ='$2a$10$CagmbDWEUEXBxewe7L6bHe0x8V0bhsIehHd2ev.mWNTTogMmG3wgm' WHERE id =2@@
UPDATE administrator SET password ='$2a$10$pQASw5w9fGSKyCwiJFh.9uNf1FQtiY9EzWG.f5U4aTPRQfRv5NYDm' WHERE id =3@@
UPDATE administrator SET password ='$2a$10$qTUN/eKKwPx7cjywRRM0vu.Ldth5pHYEbcLvhY47ZFtN9NBTQvxzW' WHERE id =4@@
UPDATE administrator SET password ='$2a$10$CqGr3hEH3za74pPF6CW13OiohUCysrvl3CAwEK12SL9SMeMwWxDae' WHERE id =5@@
UPDATE administrator SET password ='$2a$10$0l4H/jhnrrt0sulSMAnyL.62XgHXWw6ZzNoSxRvJ5qakYli4HonaC' WHERE id =6@@
UPDATE administrator SET password ='$2a$10$PMcyLWhLekDXZuOdTeRvrOjmo6cukz05vGtTk7VrRDvnCEbki0sq.' WHERE id =7@@
UPDATE administrator SET password ='$2a$10$eJTxLOG4WLUoIzH6z0lvY.eb9cW3DqTavEBC9CAdVcFnPAFxE4T.e' WHERE id =8@@