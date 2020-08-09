package com.unciv.ui.pickerscreens

import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.unciv.UncivGame
import com.unciv.logic.civilization.CivilizationInfo
import com.unciv.models.UncivSound
import com.unciv.models.translations.tr
import com.unciv.ui.utils.ImageGetter
import com.unciv.ui.utils.onClick

class UnVotePickerScreen(val civInfo:CivilizationInfo) : PickerScreen() {

    init {
        closeButton.isVisible=false
        rightSideButton.setText("Choose civilization to vote for".tr())

        var pickedCiv = civInfo;

        for (civ in civInfo.gameInfo.civilizations.filter { it != civInfo.gameInfo.getBarbarianCivilization() && it.isMajorCiv()})
        {
            val button = Button(skin)

            button.add(ImageGetter.getNationIcon(civ.nation.name)).size(30f).pad(10f)
            button.add(civ.civName).pad(10f)
            button.pack()
            button.onClick {
                pickedCiv = civ;
                pick("Vote".tr())
                descriptionLabel.setText(pickedCiv.civName.tr())
            }
            topTable.add(button).pad(10f).row()
        }

        rightSideButton.onClick(UncivSound.Paper) {
            ++pickedCiv.unVotes
            civInfo.voted = true
            UncivGame.Current.setWorldScreen();
        }

    }
}