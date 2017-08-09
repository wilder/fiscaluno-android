package com.fiscaluno.view.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.fiscaluno.R;
import com.fiscaluno.model.Institution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilder on 16/07/17.
 * TODO: Convert to Kotlin
 */
public class InstitutionAutoCompleteAdapter extends ArrayAdapter<Institution> {
    private LayoutInflater layoutInflater;
    private List<Institution> institutions;

    private Filter mFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return ((Institution)resultValue).getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null) {
                ArrayList<Institution> suggestions = new ArrayList<>();
                for (Institution inst : institutions) {
                    if (inst.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(inst);
                    }
                }

                results.values = suggestions;
                results.count = suggestions.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            if (results != null && results.count > 0) {
                // we have filtered results
                addAll((ArrayList<Institution>) results.values);
            } else {
                // no filter, add entire original list back in
            }
            notifyDataSetChanged();
        }
    };

    public InstitutionAutoCompleteAdapter(Context context, int textViewResourceId, List<Institution> mInstitutions) {
        super(context, textViewResourceId, mInstitutions);
        // copy all the customers into a master list
        institutions = new ArrayList<>(mInstitutions.size());
        institutions.addAll(mInstitutions);
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_institution_name, null);
        }

        Institution customer = getItem(position);

        TextView name = (TextView) view.findViewById(R.id.institution_name_tv_item);
        name.setText(customer.getName());

        return view;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
}