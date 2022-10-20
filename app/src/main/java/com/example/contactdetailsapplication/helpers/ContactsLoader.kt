package com.example.contactdetailsapplication.helpers

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import com.example.contactdetailsapplication.helpers.models.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


object ContactsLoader {
    var contactList: ArrayList<Contact> = ArrayList()

    private val PROJECTION = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.Contacts.HAS_PHONE_NUMBER,
        ContactsContract.Contacts.PHOTO_URI
    )

    @SuppressLint("Range")
    fun getContactList(activity: Activity, callback: () -> Unit) {
        GlobalScope.launch(Main) {
            CoroutineScope(IO).async {
                val cr: ContentResolver = activity.contentResolver
                val cursor: Cursor? = cr.query(
                    ContactsContract.Contacts.CONTENT_URI,
                    PROJECTION,
                    null,
                    null,
                    null
                )
                if (cursor != null) {
                    try {
                        val nameIndex: Int =
                            cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                        val photoIndex: Int =
                            cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)


                        var name: String
                        var photoUri: String? = null
                        while (cursor.moveToNext()) {

                            val id =
                                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))

                            var email = ""
                            var mobileNumber = ""
                            var birthday = ""
                            var phoneNumber = ""
                            var faxNumber = ""

                            email = retrieveEmail(cr, id)
                            mobileNumber = retrieveMobileNumber(cr, id)

                            phoneNumber = mobileNumber
                            faxNumber = mobileNumber
                            birthday = retrieveBirthday(cr, id)

                            name = cursor.getString(nameIndex)
                            photoUri =
                                if (cursor.getString(photoIndex) == null) "" else cursor.getString(
                                    photoIndex
                                )

                            val contact =
                                Contact(
                                    name,
                                    photoUri.toString(),
                                    phoneNumber,
                                    faxNumber,
                                    email,
                                    mobileNumber,
                                    birthday
                                )
                            Log.d("mycontacts", contact.toString())
                            contactList.add(contact)
                        }

                    } finally {
                        cursor.close()
                    }
                }
            }.await()
            callback.invoke()
        }
    }

    @SuppressLint("Range")
    private fun retrieveEmail(cr: ContentResolver, id: String): String {
        val ce = cr.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
            arrayOf(id),
            null
        )
        var email = ""
        if (ce != null && ce.moveToFirst()) {
            email = ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
            ce.close()
        }
        return email
    }

    @SuppressLint("Range")
    private fun retrieveMobileNumber(cr: ContentResolver, id: String): String {
        val cp = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(id),
            null
        )
        var number = ""
        if (cp != null && cp.moveToFirst()) {
            number =
                cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            cp.close()
        }
        return number
    }

    @SuppressLint("Range")
    private fun retrieveFaxNumber(cr: ContentResolver, id: String): String {
        val cp = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(id),
            null
        )
        var number = ""
        if (cp != null && cp.moveToFirst()) {
            number =
                cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER.toString()))
            cp.close()
        }
        return number
    }

    @SuppressLint("Range")
    private fun retrievePhoneNumber(cr: ContentResolver, id: String): String {
        val cp = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(id),
            null
        )
        var number = ""
        if (cp != null && cp.moveToFirst()) {
            number =
                cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            cp.close()
        }
        return number
    }

    @SuppressLint("Range")
    private fun retrieveBirthday(cr: ContentResolver, id: String): String {

        val cp: Cursor? = cr.query(
            ContactsContract.Data.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Event.DATA),
            ContactsContract.Data.CONTACT_ID + " = " + id + " AND " + ContactsContract.Contacts.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Event.TYPE + " = " + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY,
            null,
            ContactsContract.Data.DISPLAY_NAME
        )

        var birthday = ""

        if (cp!!.moveToFirst()) {
            birthday = cp.getString(0)
            cp.close()
        }
        return birthday
    }

}