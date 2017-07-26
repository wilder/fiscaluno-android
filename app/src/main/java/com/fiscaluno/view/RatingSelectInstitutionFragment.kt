package com.fiscaluno.view

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.R
import com.fiscaluno.contracts.SelectInstitutionContract
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.SelectInstitutionPresenter
import com.fiscaluno.view.adapter.InstitutionListAdapter
import java.util.ArrayList

class RatingSelectInstitutionFragment : Fragment(), SelectInstitutionContract.View {

    var presenter : SelectInstitutionContract.Presenter? = null
    var institutionList: RecyclerView? = null
    var searchEt: TextInputEditText? = null
    var adapter: InstitutionListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SelectInstitutionPresenter()
        presenter?.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater!!.inflate(R.layout.fragment_rating_select_institution, container, false)
        institutionList = view.findViewById(R.id.instituitons_rv_rating) as RecyclerView
        searchEt = view.findViewById(R.id.searchInstitution_et_rating) as TextInputEditText
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.loadMainInstitutions()
    }

    companion object {
        fun newInstance(): RatingSelectInstitutionFragment {
            val fragment = RatingSelectInstitutionFragment()
            return fragment
        }
    }

    private fun setupList(institutions: ArrayList<Institution>){
        adapter = InstitutionListAdapter(institutions, (activity as RatingActivity).mViewPager!!)
        institutionList?.adapter = adapter
        institutionList?.layoutManager = LinearLayoutManager(context)
    }

    override fun updateInstitutionList(institutions: ArrayList<Institution>) {
        setupList(institutions)
    }
}
