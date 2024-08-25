package com.senseicoder.mastercookbook.features.main.ui.plan.listeners;

import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;

public interface PlanRecyclerViewListeners {

    void onBookmarkClicked(PlanDTO plan);

    void onCheckIngredientsClicked(PlanDTO plan);
}
