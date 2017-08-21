package com.fiscaluno.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.fiscaluno.R
import com.fiscaluno.helper.PreferencesManager

import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment

class IntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDepthAnimation()
        addSlide(AppIntroFragment.newInstance("Conheça as Instituições", "Veja como é estudar em qualquer lugar pela visão sincera dos alunos.", R.mipmap.ic_launcher, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance("Avaliação", "Aqui os alunos podem expor os pontos positivos e negativos, além de avaliar detalhadamente de maneira simples pontos como Infraestrutura, Corpo Docente etc", R.mipmap.ic_launcher, resources.getColor(R.color.colorAccent)))
        addSlide(AppIntroFragment.newInstance("Descubra os melhores lugare para fazer um curso", "Conseguimos saber qual os melhores lugares para se fazer um curso a partir das avaliações dos alunos", R.mipmap.ic_launcher, resources.getColor(R.color.colorPrimaryDark)))
        addSlide(AppIntroFragment.newInstance("Encontre as melhores faculdades de uma categoria", "Avaliamos segurança, infraestrutura, corpo docente, comunicação etc", R.mipmap.ic_launcher,resources.getColor(R.color.white)))
        addSlide(AppIntroFragment.newInstance("Contribua para uma educação de qualidade", "Avalie sua instituição e contribua para a melhoria dela e ajudando quem ainda não decidiu pra onde ir.", R.mipmap.ic_launcher, resources.getColor(R.color.switch_thumb_disabled_material_dark)))
    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        PreferencesManager(this).haveSeenIntro = true
        startActivity(Intent(this@IntroActivity, RatingActivity::class.java))
    }
}
