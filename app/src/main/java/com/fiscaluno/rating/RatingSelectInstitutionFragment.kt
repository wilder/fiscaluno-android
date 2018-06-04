package com.fiscaluno.rating

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import com.fiscaluno.App
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.contracts.SelectInstitutionContract
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.SelectInstitutionPresenter
import com.fiscaluno.view.adapter.InstitutionAutoCompleteAdapter
import com.fiscaluno.view.adapter.InstitutionListAdapter
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.kodein.di.Kodein


class RatingSelectInstitutionFragment : Fragment(), SelectInstitutionContract.View, Step {

    var presenter : SelectInstitutionContract.Presenter? = null
    var institutionList: RecyclerView? = null
    var searchEt: AutoCompleteTextView? = null
    var adapter: InstitutionListAdapter? = null
    lateinit var kodein: Kodein

    lateinit var dataManager: DataManager

    companion object {
        fun newInstance(): RatingSelectInstitutionFragment {
            val fragment = RatingSelectInstitutionFragment()
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is DataManager) {
            dataManager = context
        } else {
            throw IllegalStateException("Activity must implement DataManager interface!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodein = (activity?.application as App).kodein
        presenter = SelectInstitutionPresenter(kodein)
        presenter?.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_rating_select_institution, container, false)
        institutionList = view.findViewById(R.id.instituitons_rv_rating)
        searchEt = view.findViewById(R.id.searchInstitution_autoComplete)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.loadMainInstitutions()
    }

    override fun setupInstitutionAutocomplete(institutions: List<Institution>?) {
        val adapter = InstitutionAutoCompleteAdapter(context, R.layout.item_institution_name, institutions)
        searchEt?.setAdapter(adapter)
    }

    private fun setupList(institutions: List<Institution>?){
        adapter = InstitutionListAdapter(institutions, (activity as RatingActivity))
        institutionList?.adapter = adapter
        institutionList?.layoutManager = LinearLayoutManager(context)
    }

    override fun updateInstitutionList(institutions: List<Institution>?) {
        setupList(institutions)
    }

    override fun onSelected() {
    }

    override fun verifyStep(): VerificationError? {
        if(dataManager.getInstitution() == null) {
            return VerificationError(getString(R.string.must_select_institution))
        }
        return null
    }

    override fun onError(error: VerificationError) {
        Snackbar.make(view!!, error.errorMessage, Snackbar.LENGTH_SHORT).show()
    }

}
