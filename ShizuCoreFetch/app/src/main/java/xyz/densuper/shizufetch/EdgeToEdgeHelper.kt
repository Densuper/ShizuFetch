package xyz.densuper.shizufetch

import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

object EdgeToEdgeHelper {
    
    fun setup(activity: ComponentActivity, rootView: View) {
        // 1. تفعيل الميزة: المكتبة ذكية وتعمل حسب إصدار هاتف المستخدم
        activity.enableEdgeToEdge()

        // 2. حساب المسافات الآمنة (Safe Insets) وتطبيقها بدقة لمنع تداخل المحتوى
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    fun bindBottomInsetsToPadding(bottomNavView: View, scrollableView: View) {
        bottomNavView.post {
            val lp = bottomNavView.layoutParams as? android.view.ViewGroup.MarginLayoutParams
            val margin = lp?.bottomMargin ?: 0
            val height = bottomNavView.height
            val extraSpacing = (12 * bottomNavView.resources.displayMetrics.density).toInt()
            scrollableView.setPadding(
                scrollableView.paddingLeft,
                scrollableView.paddingTop,
                scrollableView.paddingRight,
                height + margin + extraSpacing
            )
        }
    }
}

