package com.myxllite.app.core.network.util

import android.os.Build
import android.util.Log
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import kotlin.collections.ArrayList

object ForceTls12Util {

    /**
     * Enables TLSv1.2 protocol (which is disabled by default)
     * on pre-Lollipop devices, as well as on Lollipop, because some issues can take place on Samsung devices.
     *
     * @param client OKHtp client builder
     * @return modified OKHtp client builder
     */
    @JvmStatic
    fun enableTls12OnPreLollipop(client: OkHttpClient.Builder): OkHttpClient.Builder {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            try {
                val trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm()
                )
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers = trustManagerFactory.trustManagers
                check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                    "Unexpected default trust managers:" + Arrays.toString(
                        trustManagers
                    )
                }
                val trustManager = trustManagers[0] as X509TrustManager

                val sc = SSLContext.getInstance("TLS")
                sc.init(null, arrayOf<TrustManager>(trustManager), null)
                client.sslSocketFactory(Tls12SocketFactory(sc.socketFactory), trustManager)

                val csModernTls = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .build()

                val csClearText = ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT)
                    .build()

                val specs = ArrayList<ConnectionSpec>().apply {
                    add(csModernTls)
                    add(csClearText)
                }

                client.connectionSpecs(specs)
            } catch (exc: Exception) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc)
            }

        }
        return client
    }
}
