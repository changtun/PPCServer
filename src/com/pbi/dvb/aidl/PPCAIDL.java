/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: H:\\workspace\\PPCServer\\src\\com\\pbi\\dvb\\aidl\\PPCAIDL.aidl
 */
package com.pbi.dvb.aidl;
public interface PPCAIDL extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.pbi.dvb.aidl.PPCAIDL
{
private static final java.lang.String DESCRIPTOR = "com.pbi.dvb.aidl.PPCAIDL";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.pbi.dvb.aidl.PPCAIDL interface,
 * generating a proxy if needed.
 */
public static com.pbi.dvb.aidl.PPCAIDL asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.pbi.dvb.aidl.PPCAIDL))) {
return ((com.pbi.dvb.aidl.PPCAIDL)iin);
}
return new com.pbi.dvb.aidl.PPCAIDL.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_checkUpdate:
{
data.enforceInterface(DESCRIPTOR);
this.checkUpdate();
reply.writeNoException();
return true;
}
case TRANSACTION_switchService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
java.lang.String _arg4;
_arg4 = data.readString();
this.switchService(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
return true;
}
case TRANSACTION_stopPPC:
{
data.enforceInterface(DESCRIPTOR);
this.stopPPC();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.pbi.dvb.aidl.PPCAIDL
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void checkUpdate() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_checkUpdate, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void switchService(java.lang.String serviceName, int serviceId, int tsId, int netId, java.lang.String mac) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
_data.writeInt(serviceId);
_data.writeInt(tsId);
_data.writeInt(netId);
_data.writeString(mac);
mRemote.transact(Stub.TRANSACTION_switchService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopPPC() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopPPC, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_checkUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_switchService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_stopPPC = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void checkUpdate() throws android.os.RemoteException;
public void switchService(java.lang.String serviceName, int serviceId, int tsId, int netId, java.lang.String mac) throws android.os.RemoteException;
public void stopPPC() throws android.os.RemoteException;
}
