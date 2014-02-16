/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.presentation.lightningtalk.ui.slides.base;

import org.jrebirth.af.presentation.ui.base.AbstractSlideModel;
import org.jrebirth.af.presentation.ui.base.SlideStep;

// TODO: Auto-generated Javadoc
/**
 * The class <strong>BasicModel</strong>.
 * 
 * @param <M> the generic type
 * @param <V> the value type
 * @param <S> the generic type
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseModel<M extends AbstractBaseModel<M, V, S>, V extends AbstractBaseView<?, ?, ?>, S extends SlideStep> extends AbstractSlideModel<M, V, S> {

    /**
     * Return the title string from the slide object.
     * 
     * @return the slide title
     */
    protected String getTitle() {
        return getSlide().getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getSlideNumber() + "/" + getStepPosition() + " " + getTitle() + this.getClass().getSimpleName();
    }

}
